package com.sofrecom.myshop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.model.User;

@Service
public class ProductServiceImpl implements ProductIService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserService userService;

	public List<Product> findAll() {
		return Arrays.asList(restTemplate.getForObject(
				"http://localhost:3000/products", Product[].class));
	}

	public Product findById(Long id) {
		return restTemplate.getForObject(
				"http://localhost:3000/products/" + id, Product.class);
	}

	public List<Product> findByCriteria(String criteria, Object key) {
		return Arrays.asList(restTemplate.getForObject(
				"http://localhost:3000/products?" + criteria + "=" + key,
				Product[].class));
	}

	public List<String> findBrands(Product[] products) {
		List<String> marques = new ArrayList<>();

		for (Product product : products) {
			if (!marques.contains(product.getBrand()))
				marques.add(product.getBrand());
		}
		return marques;
	}

	public List<Long> findRams(Product[] products) {
		List<Long> rams = new ArrayList<>();

		for (Product product : products) {
			if (!rams.contains(product.getRam()) && product.getRam() != 0)
				rams.add(product.getRam());
		}
		return rams;
	}

	public List<Long> findAppereilPhotos(Product[] products) {
		List<Long> appereilPhotos = new ArrayList<>();

		for (Product product : products) {
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
		
		StringBuilder url = new StringBuilder();

		url.append("http://localhost:3000/products?");
		
		if (!orderPrice.equals("")) {
			url.append("_sort=price&_order=" + orderPrice + "&");
		}
		if (!orderName.equals("")) {
			url.append("_sort=brand,name&_order=" + orderName + "," + orderName
					+ "&");
		}
		if (!search.equals("")) {
			url.append("q=" + search + "&");
		}
		if (!brand.equals("")) {
			String[] brands = brand.split(",");
			for (String b : brands) {
				url.append("brand=" + b + "&");
			}
		}

		if (!type.equals("all") && !type.equals("")) {
			String[] types = type.split(",");
			for (String b : types) {
				url.append("type=" + b + "&");
			}
		}

		url.append("price_gte=" + priceMin + "&price_lte=" + priceMax + "&");
		url.append("_page=" + page + "&_limit=" + limit);

		return segmentation(restTemplate.getForObject(url.toString(), Product[].class));
	}

	public List<Product> segmentation(Product[] prods) {

		List<Product> products = new ArrayList<>();

		User user = userService.findUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		
		String segment1 = user.getGender();
		String segment2="";

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
					|| ((!prod.getSegment().equals(segment1))&&(!prod.getSegment().equals(segment2)))) {
				products.add(prod);

			}
		}

		return products;
	}

}
