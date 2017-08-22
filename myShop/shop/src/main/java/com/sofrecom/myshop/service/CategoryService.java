package com.sofrecom.myshop.service;

import java.util.List;

import com.sofrecom.myshop.model.Product;

public interface CategoryService {
	
	List<Product> findProductsByCategory(String category);
	Product findProductByCategory(Long id,String category);

}
