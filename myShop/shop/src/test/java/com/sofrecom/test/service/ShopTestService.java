package com.sofrecom.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.service.ProductIService;
import com.sofrecom.myshop.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/shop-servlet.xml")
@ActiveProfiles("test")
public class ShopTestService {
	
	@Autowired
	private ProductIService service;

	@Test
	public void testAll() {
		List<Product> products = service.findAll();
		
		assertNotNull(products);
	}
	
	@Test
	public void testById() {
		Product product = service.findById(Long.valueOf(2));
		
		assertNotNull(product);
	}
	
	@Test
	public void testByCriteria() {
		List<Product> products = service.findByCriteria("type", "iphones");
		
		assertNotNull(products);
	}
	
	@Test
	public void testBrands() {
		List<String> products = service.findBrands(service.findAll().toArray(new Product[service.findAll().size()]));
		
		assertNotNull(products);
	}
	
	@Test
	public void testRams() {
		List<Long> products = service.findRams(service.findAll().toArray(new Product[service.findAll().size()]));
		
		assertNotNull(products);
	}
	
	@Test
	public void testCams() {
		List<Long> products = service.findAppereilPhotos(service.findAll().toArray(new Product[service.findAll().size()]));
		
		assertNotNull(products);
	}

}
