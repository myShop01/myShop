package com.sofrecom.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.service.CategoryService;
import com.sofrecom.myshop.service.CategoryServiceImpl;
import org.springframework.test.context.ContextConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void testFindProductsByCategory() {
		String category = "smartphones";

		List<Product> products = categoryService.findProductsByCategory(category);

		assertNotNull(products);
		assertNotEquals(products.size(), 0);
		assertEquals(products.get(0).getType(), category);
	}

	@Test
	public void testFindProductByCategory() {

		String category = "smartphones";
		Long id = 2L;

		Product product = categoryService.findProductByCategory(id, category);
		
		assertNotNull(product);
		assertEquals(product.getType(), category);
		assertEquals(product.getId(), Long.valueOf(id));
	}

	@Configuration
	static class ContextConfiguration {

		// this bean will be injected into Test class
		@Bean
		public CategoryService categoryService() {
			CategoryService categoryService = new CategoryServiceImpl();
			// set properties, etc.
			return categoryService;
		}
	}

}
