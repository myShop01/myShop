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
	
	public static final String ACCESSORIES_CHECKED = "accessories_checked";
	public static final String TABLETS_CHECKED = "tablets_checked";
	public static final String SMARTPHONES_CHECKED = "smartphones_checked";
	
	public static final String SHOP_URL="shop";
	public static final String DETAIL_URL="detail";
	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
    public String produits(Model model) {
		Product[] products = productService.findAll();
        model.addAttribute(PRODUCT_MODEL, products);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(products));
        return SHOP_URL;
    }
	
	
	@RequestMapping(value = "/accessories", method = RequestMethod.GET)
    public String accessories(Model model) {
		List<Product> prods = categoryService.findProductsByCategory("accessories");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
        model.addAttribute(ACCESSORIES_CHECKED,true);
        return SHOP_URL;
    }
	
	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
    public String smartphones(Model model) {
		List<Product> prods = categoryService.findProductsByCategory("smartphones");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
        model.addAttribute(SMARTPHONES_CHECKED,true);
        return SHOP_URL;
    }
	
	@RequestMapping(value = "/tablets", method = RequestMethod.GET)
    public String tablettes(Model model) {
		List<Product> prods = categoryService.findProductsByCategory("tablets");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods.toArray(new Product[prods.size()])));
        model.addAttribute(TABLETS_CHECKED,true);
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
	
}
