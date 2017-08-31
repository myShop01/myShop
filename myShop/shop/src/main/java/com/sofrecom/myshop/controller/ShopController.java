package com.sofrecom.myshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.model.ProductPromotion;
import com.sofrecom.myshop.service.CategoryService;
import com.sofrecom.myshop.service.ProductIService;
import com.sofrecom.myshop.service.PromotionService;

@Controller
public class ShopController {

	@Autowired
	ProductIService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PromotionService promotionService;

	public static final String PRODUCT_MODEL = "products";
	public static final String BRANDS_MODEL = "marques";

	public static final String SHOP_URL = "shop";
	public static final String DETAIL_URL = "detail";
	public static final String PROMOS_URL = "promos";

	public static final String TYPE_ON_LOAD = "typeOnLoad";
	public static final String ORDER_PRICE_ON_LOAD = "orderPriceOnLoad";
	public static final String ORDER_NAME_ON_LOAD = "orderNameOnLoad";
	public static final String SEARCH_ON_LOAD = "searchOnLoad";
	public static final String BRAND_ON_LOAD = "brandOnLoad";
	public static final String PRICE_MIN_ON_LOAD = "priceMinOnLoad";
	public static final String PRICE_MAX_ON_LOAD = "priceMaxOnLoad";
	public static final String PAGE_ON_LOAD = "pageOnLoad";

	public static final String RECORD_SIZE = "recordsSize";

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String produits(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute(PRODUCT_MODEL, products);
		model.addAttribute(BRANDS_MODEL, productService.findBrands(products.toArray(new Product[products.size()])));
		model.addAttribute(TYPE_ON_LOAD, "all");
		return SHOP_URL;
	}

	@RequestMapping(value = "/promos", method = RequestMethod.GET)
	public String promos(Model model) {
		List<Product> products = productService.findProductsPromotions();
		
		model.addAttribute(PRODUCT_MODEL, products);
		model.addAttribute(BRANDS_MODEL, productService.findBrands(products.toArray(new Product[products.size()])));
		model.addAttribute(TYPE_ON_LOAD, "all");
		return PROMOS_URL;
	}

	@RequestMapping(value = "/accessories", method = RequestMethod.GET)
	public String accessories(Model model) {
		List<Product> prods = categoryService.findProductsByCategory("accessories");
		model.addAttribute(PRODUCT_MODEL, prods);
		model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
		model.addAttribute(TYPE_ON_LOAD, "accessories");
		return SHOP_URL;
	}

	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
	public String smartphones(Model model) {
		List<Product> prods = categoryService.findProductsByCategory("smartphones");
		model.addAttribute(PRODUCT_MODEL, prods);
		model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
		model.addAttribute(TYPE_ON_LOAD, "smartphones");
		return SHOP_URL;
	}

	@RequestMapping(value = "/tablets", method = RequestMethod.GET)
	public String tablettes(Model model) {

		List<Product> prods = categoryService.findProductsByCategory("tablets");
		model.addAttribute(PRODUCT_MODEL, prods);
		model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
		model.addAttribute(TYPE_ON_LOAD, "tablets");
		return SHOP_URL;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String detail(@RequestParam(value = "id", required = false, defaultValue = "0") Long id, Model model) {
		Product prod = productService.findById(id);
		model.addAttribute("phone", prod);
		return DETAIL_URL;
	}

	@RequestMapping(value = "/testMenu", method = RequestMethod.GET)
	public String testmenu(@RequestParam(value = "id", required = false, defaultValue = "0") Long id, Model model) {
		return "testmenu";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(value = "search", required = false, defaultValue = "") String serach,
			Model model) {
		model.addAttribute("searchedItem", serach);
		model.addAttribute(BRANDS_MODEL, productService
				.findBrands(productService.findAll().toArray(new Product[productService.findAll().size()])));
		return "productsSearch";
	}

	@RequestMapping(value = "/options/{viewId}", method = RequestMethod.GET)
	public String options(@PathVariable("viewId") String viewId,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "orderPrice", required = false, defaultValue = "") String orderPrice,
			@RequestParam(value = "orderName", required = false, defaultValue = "") String orderName,
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@RequestParam(value = "brand", required = false, defaultValue = "") String brand,
			@RequestParam(value = "priceMin", required = false, defaultValue = "0") String priceMin,
			@RequestParam(value = "priceMax", required = false, defaultValue = "2000") String priceMax,
			@RequestParam(value = "page", required = false, defaultValue = "1") String page, Model model) {
		
		List<Product> listProds ;
		List<Product> prodsPromos ;
		
		List<Product> prods = new ArrayList<>();
		String limit = "15";
		String query = null;

		if (!"productspromos".equals(viewId)) {
			
			query = productService.getQueryProductsPromos();

			prodsPromos = productService.findByFiltersAndQuery(query, type, orderPrice, orderName, search, brand, priceMin,
					priceMax, page, limit);
			
			listProds = productService.findByFilters(type, orderPrice, orderName, search, brand, priceMin, priceMax, page,
					limit);
			
			
			
			for(Product product: listProds){
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
				prods.add(productPromotion);
			}
			
			for(Product product: prods){
				for(Product promo: prodsPromos){
					if(product.getId()==promo.getId()) { ((ProductPromotion)product).setPromotionPrice(((ProductPromotion)promo).getPromotionPrice());
					}
				}
			}
			
			query=null;
		} else {
			query = productService.getQueryProductsPromos();

			prods = productService.findByFiltersAndQuery(query, type, orderPrice, orderName, search, brand, priceMin,
					priceMax, page, limit);
		}
		
		model.addAttribute(PRODUCT_MODEL, prods);
		model.addAttribute(TYPE_ON_LOAD, type);
		model.addAttribute(ORDER_PRICE_ON_LOAD, orderPrice);
		model.addAttribute(ORDER_NAME_ON_LOAD, orderName);
		model.addAttribute(SEARCH_ON_LOAD, search);
		model.addAttribute(BRAND_ON_LOAD, brand);
		model.addAttribute(PRICE_MIN_ON_LOAD, priceMin);
		model.addAttribute(PRICE_MAX_ON_LOAD, priceMax);
		model.addAttribute(PAGE_ON_LOAD, page);

		model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
		List<Product> record = query !=null ? productService.findByFiltersAndQuery(query,type, orderPrice, orderName, search, brand, priceMin, priceMax, "", "") : productService.findByFilters(type, orderPrice, orderName, search, brand, priceMin, priceMax, "", "");
		model.addAttribute(RECORD_SIZE, record.size());

		return viewId;
	}

}
