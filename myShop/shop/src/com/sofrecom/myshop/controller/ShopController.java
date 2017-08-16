package com.sofrecom.myshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.Product;

@Controller
public class ShopController {
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//    public String redirect(Model model) {
//        return "redirect:/products";
//    }
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
    public String produits(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		List<Product> prods = restTemplate.getForObject("http://localhost:3000/products", List.class);
        model.addAttribute("products", prods);
        model.addAttribute("activetab", "all");
        return "shop";
    }
	
	
	@RequestMapping(value = "/iphones", method = RequestMethod.GET)
    public String iphones(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		List<Product> prods = restTemplate.getForObject("http://localhost:3000/products?type=iphones", List.class);
        model.addAttribute("products", prods);
        model.addAttribute("activetab", "iphones");
        return "shop";
    }
	
	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
    public String smartphones(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		List<Product> prods = restTemplate.getForObject("http://localhost:3000/products?type=smartphones", List.class);
        model.addAttribute("products", prods);
        model.addAttribute("activetab", "smartphones");
        return "shop";
    }
	
	@RequestMapping(value = "/tablettes", method = RequestMethod.GET)
    public String tablettes(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		List<Product> prods = restTemplate.getForObject("http://localhost:3000/products?type=tablettes", List.class);
        model.addAttribute("products", prods);
        model.addAttribute("activetab", "tablettes");
        return "shop";
    }
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
    public String detail(@RequestParam(value="id", required=false, defaultValue="0") String id, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Product prod = restTemplate.getForObject("http://localhost:3000/products/"+id, Product.class);
		model.addAttribute("phone", prod);
        return "detail";
    }
	
}
