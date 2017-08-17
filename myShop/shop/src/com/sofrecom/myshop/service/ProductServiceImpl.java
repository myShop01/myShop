package com.sofrecom.myshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.Product;


@Service
public class ProductServiceImpl implements ProductIService{
	
	@Autowired
	RestTemplate restTemplate;
	
	public Product[] findAll() {
		return restTemplate.getForObject("http://localhost:3000/products", Product[].class);
	}

	public Product findById(Long id) {
		return restTemplate.getForObject("http://localhost:3000/products/"+id, Product.class);
	}

	public Product[] findByCriteria(String criteria, Object key) {
		return restTemplate.getForObject("http://localhost:3000/products?"+criteria+"="+key, Product[].class);
	}
	
	public List<String> findBrands(Product[] products){
		List<String> marques = new ArrayList<>();
		
		for(Product product: products){
			if(!marques.contains(product.getBrand())) marques.add(product.getBrand());
		}
		return marques;
	}
	
	public List<Long> findRams(Product[] products){
		List<Long> rams = new ArrayList<>();
		
		for(Product product: products){
			if(!rams.contains(product.getRam())&&product.getRam()!=0) rams.add(product.getRam());
		}
		return rams;
	}
	
	public List<Long> findAppereilPhotos(Product[] products){
		List<Long> appereilPhotos = new ArrayList<>();
		
		for(Product product: products){
			if(!appereilPhotos.contains(product.getAppareilPhoto())&&product.getAppareilPhoto()!=0) appereilPhotos.add(product.getAppareilPhoto());
		}
		return appereilPhotos;
	}
	

}
