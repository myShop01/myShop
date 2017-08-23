package com.sofrecom.myshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.service.ProductIService;

@Controller
public class ShopController {
	
	@Autowired
	ProductIService productService;
	
	
	public static final String PRODUCT_MODEL = "products";
	public static final String BRANDS_MODEL = "marques";
	
	public static final String SHOP_URL="shop";
	public static final String DETAIL_URL="detail";
	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
    public String produits(Model model) {
		Product[] products = productService.findAll();
        model.addAttribute(PRODUCT_MODEL, products);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(products));
        return SHOP_URL;
    }
	
	
	@RequestMapping(value = "/iphones", method = RequestMethod.GET)
    public String iphones(@RequestParam(value="pricemin", required=false, defaultValue="0") Long pricemin,
    		              @RequestParam(value="pricemax", required=false, defaultValue="2000") Long pricemax,
    		              Model model) {
		Product[] prods = productService.findByCriteria("type", "iphones");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods));
        return SHOP_URL;
    }
	
	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
    public String smartphones(Model model) {
		Product[] prods = productService.findByCriteria("type", "smartphones");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods));
        return SHOP_URL;
    }
	
	@RequestMapping(value = "/tablettes", method = RequestMethod.GET)
    public String tablettes(Model model) {
		Product[] prods = productService.findByCriteria("type", "tablettes");
        model.addAttribute(PRODUCT_MODEL, prods);
        model.addAttribute(BRANDS_MODEL, productService.findBrands(prods));
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
