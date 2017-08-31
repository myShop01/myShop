package com.sofrecom.myshop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.model.User;
import com.sofrecom.myshop.model.ProductPromotion;
import com.sofrecom.myshop.model.SpecificPromotion;

@Service
public class ProductServiceImpl implements ProductIService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserService userService;
	
	@Autowired
	PromotionService promotionService;

	public static final String URL_PRODUCTS = "http://localhost:3000/products";

	@Override
	public List<Product> findAll() {
		return Arrays.asList(restTemplate.getForObject(URL_PRODUCTS,
				Product[].class));
	}

	@Override
	public Product findById(Long id) {
		return restTemplate
				.getForObject(URL_PRODUCTS + "/" + id, Product.class);
	}

	@Override
	public List<Product> findByCriteria(String criteria, Object key) {
		return Arrays.asList(restTemplate.getForObject(URL_PRODUCTS + "?"
				+ criteria + "=" + key, Product[].class));
	}

	@Override
	public List<String> findBrands(Product[] products) {
		List<String> marques = new ArrayList<>();

		for (Product product : products) {
			if (!marques.contains(product.getBrand()))
				marques.add(product.getBrand());
		}
		return marques;
	}

	@Override
	public List<Long> findRams(Product[] products) {
		List<Long> rams = new ArrayList<>();

		for (Product product : products) {
			if (!rams.contains(product.getRam()) && product.getRam() != 0)
				rams.add(product.getRam());
		}
		return rams;
	}

	@Override
	public List<Long> findAppereilPhotos(Product[] products) {
		List<Long> appereilPhotos = new ArrayList<>();

		for (Product product : products) {
			if (!appereilPhotos.contains(product.getAppareilPhoto())
					&& product.getAppareilPhoto() != 0)
				if (!appereilPhotos.contains(product.getAppareilPhoto())
						&& product.getAppareilPhoto() != 0)
					appereilPhotos.add(product.getAppareilPhoto());
		}
		return appereilPhotos;
	}

	@Override
	public List<Product> findByFilters(String type, String orderPrice,
			String orderName, String search, String brand, String priceMin,
			String priceMax, String page, String limit) {

		StringBuilder url = new StringBuilder(URL_PRODUCTS + "?");

		if (!"".equals(orderPrice)) {
			url.append("_sort=price&_order=" + orderPrice + "&");
		}
		if (!"".equals(orderName)) {
			url.append("_sort=brand,name&_order=" + orderName + "," + orderName
					+ "&");
		}
		if (!"".equals(search)) {
			url.append("q=" + search + "&");
		}
		if (!"".equals(brand)) {
			String[] brands = brand.split(",");
			for (String b : brands) {
				url.append("brand=" + b + "&");
			}
		}

		if (!"".equals(type) && !"all".equals(type)) {
			String[] types = type.split(",");
			for (String b : types) {
				url.append("type=" + b + "&");
			}
		}

		url.append("price_gte=" + priceMin + "&price_lte=" + priceMax + "&");
		url.append("_page=" + page + "&_limit=" + limit);

		return segmentation(restTemplate.getForObject(url.toString(),
				Product[].class));
	}

	public List<Product> segmentation(Product[] prods) {

		List<Product> products = new ArrayList<>();

		User user = userService.findUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());

		String segment1 = user.getGender();
		String segment2 = "";

		if (segment1.equals("M")) {
			segment2 = "F";
		} else {
			segment2 = "M";
		}

		if (user.getAge() < 15) {
			segment1 += "E";
			segment2 += "E";
		} else if (user.getAge() > 15) {
			if (user.getAge() < 25) {
				segment1 += "A";
				segment2 += "A";
			} else {
				segment1 += "AD";
				segment2 += "AD";

			}
		}

		for (Product prod : prods) {

			if (prod.getSegment() != null && prod.getSegment().equals(segment1)) {
				products.add(prod);

			}
		}

		for (Product prod : prods) {

			if (prod.getSegment() != null && prod.getSegment().equals(segment2)) {
				products.add(prod);

			}
		}

		for (Product prod : prods) {

			if (prod.getSegment() == null
					|| ((!prod.getSegment().equals(segment1)) && (!prod
							.getSegment().equals(segment2)))) {
				products.add(prod);

			}
		}

		return products;

	}

	@Override
	public List<Product> findByFiltersAndQuery(String query, String type,
			String orderPrice, String orderName, String search, String brand,
			String priceMin, String priceMax, String page, String limit) {
		List<Product> productsPromotion = new ArrayList<>();
		StringBuilder url = new StringBuilder(query);

		if (!"".equals(orderPrice)) {
			url.append("_sort=price&_order=" + orderPrice + "&");
		}
		if (!"".equals(orderName)) {
			url.append("_sort=brand,name&_order=" + orderName + "," + orderName
					+ "&");
		}
		if (!"".equals(search)) {
			url.append("q=" + search + "&");
		}
		if (!"".equals(brand)) {
			String[] brands = brand.split(",");
			for (String b : brands) {
				url.append("brand=" + b + "&");
			}
		}

		if (!"".equals(type) && !"all".equals(type)) {
			String[] types = type.split(",");
			for (String b : types) {
				url.append("type=" + b + "&");
			}
		}
		
		if (!"".equals(priceMin) && !"".equals(priceMax)) {
			url.append("price_gte=" + priceMin + "&price_lte=" + priceMax + "&");
		}
		
		url.append("_page=" + page + "&_limit=" + limit);

		List<Product> products = Arrays.asList(restTemplate.getForObject(
				new String(url), Product[].class));

		for (Product product : products) {
			ProductPromotion productPromotion = new ProductPromotion();
			productPromotion.setId(product.getId());
			productPromotion.setAppareilPhoto(product.getAppareilPhoto());
			productPromotion.setBrand(product.getBrand());
			productPromotion.setDescription(product.getDescription());
			productPromotion.setIsAvailable(product.getIsAvailable());
			productPromotion.setName(product.getName());
			productPromotion.setPrice(product.getPrice());
			productPromotion.setRam(product.getRam());
			productPromotion.setSegment(product.getSegment());
			productPromotion.setType(product.getType());
			productPromotion.setUrl(product.getUrl());

			SpecificPromotion promotion = promotionService.findPromotions()
					.stream().filter(s -> s.getId().equals(product.getId()))
					.findFirst().get();

			productPromotion.setPromotionPrice(promotion.getPrice());
			productPromotion
					.setPromotionDescription(promotion.getDescription());

			productsPromotion.add(productPromotion);
		}
		return productsPromotion;
	}

	@Override
	public List<Product> findByIds(List<Long> ids) {
		// TODO: add test method
		StringBuilder query = new StringBuilder("id=" + ids.get(0) + "&");

		for (int i = 1; i < ids.size(); i++) {
			query.append("id=" + ids.get(i) + "&");
		}

		return Arrays.asList(restTemplate.getForObject(URL_PRODUCTS + "?"
				+ query, Product[].class));
	}
	
	@Override
	public String getQueryProductPromo(Long id) {

		StringBuilder query = new StringBuilder("id=" + id + "&");
		return URL_PRODUCTS + "?" + query;
	}

	@Override
	public String getQueryProductsPromos() {
		List<Long> ids = promotionService.findPromotions().stream()
				.map(SpecificPromotion::getId).collect(Collectors.toList());

		StringBuilder query = new StringBuilder("id=" + ids.get(0) + "&");

		for (int i = 1; i < ids.size(); i++) {
			query.append("id=" + ids.get(i) + "&");
		}

		return URL_PRODUCTS + "?" + query;
	}

	@Override
	public List<Product> findProductsPromotions() {
		// TODO: add test method
		List<Long> ids = promotionService.findPromotions().stream()
				.map(SpecificPromotion::getId).collect(Collectors.toList());
		return this.findByIds(ids);
	}

}
