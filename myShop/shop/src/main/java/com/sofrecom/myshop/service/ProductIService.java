package com.sofrecom.myshop.service;

import java.util.List;

import com.sofrecom.myshop.model.Product;


public interface ProductIService {
	
	public List<Product> findAll();
	public List<Product> findByIds(List<Long> id);
	public List<Product> findProductsPromotions();
	public String getQueryProductsPromos();
	public String getQueryProductPromo(Long id);
	public Product findById(Long id);
	public List<Product> findByCriteria(String criteria, Object key);
	public List<String> findBrands(Product[] products);
	public List<Long> findAppereilPhotos(Product[] products);
	public List<Long> findRams(Product[] products);
	public List<Product> findByFilters(String type, String orderPrice,
			String orderName, String search, String brand, String priceMin, String priceMax);
	public List<Product> findByFiltersAndQuery(String query,String type, String orderPrice,
			String orderName, String search, String brand, String priceMin, String priceMax);
}
