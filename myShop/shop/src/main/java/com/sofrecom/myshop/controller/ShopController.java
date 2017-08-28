package com.sofrecom.myshop.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.service.CategoryService;
import com.sofrecom.myshop.service.ProductIService;

@Controller
public class ShopController {
	
	@Autowired
	ProductIService productService;
	
	@Autowired
	CategoryService categoryService;
	
	
	public static final String PRODUCT_MODEL = "products";
	public static final String BRANDS_MODEL = "marques";
	
	public static final String SHOP_URL="shop";
	public static final String DETAIL_URL="detail";
	
	public static final String TYPE_ON_LOAD="typeOnLoad";
	
	public static final String RECORD_SIZE="recordsSize";
	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
    public String produits(Model model) {
		List<Product> products = productService.findAll();
        model.addAttribute(PRODUCT_MODEL, products);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(products.toArray(new Product[products.size()])));
        model.addAttribute(TYPE_ON_LOAD,"all");
        return SHOP_URL;
    }
	
	
	@RequestMapping(value = "/accessories", method = RequestMethod.GET)
    public String accessories(Model model) {
		List<Product> prods = categoryService.findProductsByCategory("accessories");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
        model.addAttribute(TYPE_ON_LOAD,"accessories");
        return SHOP_URL;
    }
	
	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
    public String smartphones(Model model) {
		List<Product> prods = categoryService.findProductsByCategory("smartphones");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
        model.addAttribute(TYPE_ON_LOAD,"smartphones");
        return SHOP_URL;
    }
	
	@RequestMapping(value = "/tablets", method = RequestMethod.GET)
    public String tablettes(Model model) {

		List<Product> prods = categoryService.findProductsByCategory("tablets");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
        model.addAttribute(TYPE_ON_LOAD,"tablettes");
        return SHOP_URL;
    }
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
    public String detail(@RequestParam(value="id", required=false, defaultValue="0") Long id, Model model) {
		Product prod = productService.findById(id);
		model.addAttribute("phone", prod);
        return DETAIL_URL;
    }
	
	@RequestMapping(value = "/testMenu", method = RequestMethod.GET)
    public String testmenu(@RequestParam(value="id", required=false, defaultValue="0") Long id, Model model) {
        return "testmenu";
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value="search", required=false, defaultValue="") String serach, Model model) {
		model.addAttribute("searchedItem",serach);
		model.addAttribute(BRANDS_MODEL, productService.findBrands(productService.findAll().toArray(new Product[productService.findAll().size()])));
        return "productsSearch";
    }
	
	@RequestMapping(value = "/options", method = RequestMethod.GET)
    public String options(@RequestParam(value="type", required=false, defaultValue="") String type,
    		@RequestParam(value="orderPrice", required=false, defaultValue="") String orderPrice,
    		@RequestParam(value="orderName", required=false, defaultValue="") String orderName,
    		@RequestParam(value="search", required=false, defaultValue="") String search,
    		@RequestParam(value="brand", required=false, defaultValue="") String brand,
    		@RequestParam(value="priceMin", required=false, defaultValue="0") String priceMin,
    		@RequestParam(value="priceMax", required=false, defaultValue="2000") String priceMax,
    		Model model) {
		List<Product> prods = productService.findByFilters(type, orderPrice, orderName, search, brand, priceMin, priceMax);
		
		model.addAttribute(TYPE_ON_LOAD,type);
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
        model.addAttribute(RECORD_SIZE, prods.size());
        
        return "products";
    }
	
}
