package com.sofrecom.myshop.service;

import java.util.List;

import com.sofrecom.myshop.model.Product;

public interface ProductIService {
	
	public Product[] findAll();
	public Product findById(Long id);
	public Product[] findByCriteria(String criteria, Object key);
	public List<String> findBrands(Product[] products);
	public List<Long> findAppereilPhotos(Product[] products);
	public List<Long> findRams(Product[] products);
}
