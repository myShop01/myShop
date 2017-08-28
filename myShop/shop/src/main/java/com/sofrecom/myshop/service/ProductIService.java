package com.sofrecom.myshop.service;

import java.util.List;

import com.sofrecom.myshop.model.Product;

public interface ProductIService {
	
	public List<Product> findAll();
	public Product findById(Long id);
	public List<Product> findByCriteria(String criteria, Object key);
	public List<String> findBrands(Product[] products);
	public List<Long> findAppereilPhotos(Product[] products);
	public List<Long> findRams(Product[] products);
	public List<Product> findByFilters(String type, String orderPrice,
			String orderName, String search, String brand, String priceMin, String priceMax);
}
