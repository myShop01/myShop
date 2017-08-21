package com.sofrecom.myshop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.Product;

public class CategoryServiceImpl implements CategoryService {

	public static final String REST_URI_PREFIX = "http://localhost:3000";

	@Override
	public List<Product> findProductsByCategory(String category) {
		RestTemplate restTemplate = new RestTemplate();
		List<Product> listProducts = new ArrayList<>();

		Product[] products = restTemplate.getForObject(REST_URI_PREFIX + "/products?type=" + category, Product[].class);
		if (products.length > 0) {
			listProducts = Arrays.asList(products);
		}

		return listProducts;
	}

	@Override
	public Product findProductByCategory(Long id, String category) {
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();

		Product productResult = restTemplate.getForObject(REST_URI_PREFIX + "/products/" + id + "?type=" + category,
				Product.class);
		if (productResult != null) {
			product = productResult;
		}

		return product;
	}

}
