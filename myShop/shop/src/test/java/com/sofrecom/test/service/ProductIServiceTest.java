package com.sofrecom.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.service.ProductIService;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/shop-servlet.xml")
@ActiveProfiles("test")
public class ProductIServiceTest {
	
	@Autowired
	@InjectMocks
	ProductIService productService;

	@Test
	public void testFindAll() {
		List<Product> products = productService.findAll();
		assertNotNull(products);
	}

	@Test
	public void testFindByIds() {
				
		List<Long> ids = new ArrayList<>();
		Long id1 = 1L;
		Long id2 = 2L;
		
		ids.add(id1);
		ids.add(id2);
		
		List<Product> products = productService.findByIds(ids);
		assertNotNull(products);
		assertNotEquals(products.get(0).getId(),id2);
		assertEquals(products.get(1).getId(),id2);
		
	}

	@Test
	public void testFindProductsPromotions() {
		List<Product> products = productService.findProductsPromotions();
		assertNotNull(products);
	}

	@Test
	public void testGetQueryProductsPromos() {
		String query = productService.getQueryProductsPromos();
		
		assertNotNull(query);
		assertNotEquals(query, "");
	}

	@Test
	public void testGetQueryProductPromo() {
		String query = productService.getQueryProductPromo(1L);
		
		assertNotNull(query);
		assertNotEquals(query.contains("id=1"), false);
	}

	@Test
	public void testFindById() {
		Long id = 1L;
		
		Product product = productService.findById(id);
		
		assertNotNull(product);
		assertEquals(product.getId(), id);
	}

	@Test
	public void testFindBrands() {
		List<Product> products = productService.findAll();
		assertNotNull(products);
		assertNotEquals(products.size(),0);
		List<String> brands = productService.findBrands((Product[])products.toArray());
		assertNotNull(brands);
	}


}
