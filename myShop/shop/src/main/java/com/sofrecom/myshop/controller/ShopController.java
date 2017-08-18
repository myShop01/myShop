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
	
	
	String produc = "products";
	String activetab = "activetab";
	String marques = "marques";
	String appareilsPhotos = "appareilsPhotos";
	String rams = "rams";
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//    public String redirect(Model model) {
//        return "redirect:/products";
//    }
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
    public String produits(Model model) {
		Product[] products = productService.findAll();
        model.addAttribute(produc, products);
        model.addAttribute(activetab, "all");
        model.addAttribute(marques, productService.findBrands(products));
        model.addAttribute(rams, productService.findRams(products));
        model.addAttribute(appareilsPhotos, productService.findAppereilPhotos(products));
        return "shop";
    }
	
	
	@RequestMapping(value = "/iphones", method = RequestMethod.GET)
    public String iphones(@RequestParam(value="pricemin", required=false, defaultValue="0") Long pricemin,
    		              @RequestParam(value="pricemax", required=false, defaultValue="2000") Long pricemax,
    		              Model model) {
		Product[] prods = productService.findByCriteria("type", "iphones");
        model.addAttribute(produc, prods);
        model.addAttribute(activetab, "iphones");
        model.addAttribute(marques, productService.findBrands(prods));
        model.addAttribute(rams, productService.findRams(prods));
        model.addAttribute(appareilsPhotos, productService.findAppereilPhotos(prods));
        return "shop";
    }
	
	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
    public String smartphones(Model model) {
		Product[] prods = productService.findByCriteria("type", "smartphones");
        model.addAttribute(produc, prods);
        model.addAttribute(activetab, "smartphones");
        model.addAttribute(marques, productService.findBrands(prods));
        model.addAttribute(rams, productService.findRams(prods));
        model.addAttribute(appareilsPhotos, productService.findAppereilPhotos(prods));
        return "shop";
    }
	
	@RequestMapping(value = "/tablettes", method = RequestMethod.GET)
    public String tablettes(Model model) {
		Product[] prods = productService.findByCriteria("type", "tablettes");
        model.addAttribute(produc, prods);
        model.addAttribute(activetab, "tablettes");
        model.addAttribute(marques, productService.findBrands(prods));
        model.addAttribute(rams, productService.findRams(prods));
        model.addAttribute(appareilsPhotos, productService.findAppereilPhotos(prods));
        return "shop";
    }
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
    public String detail(@RequestParam(value="id", required=false, defaultValue="0") Long id, Model model) {
		Product prod = productService.findById(id);
		model.addAttribute("phone", prod);
        return "detail";
    }
	
}
