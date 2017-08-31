package com.sofrecom.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sofrecom.myshop.model.SpecificPromotion;
import com.sofrecom.myshop.service.ProductIService;
import com.sofrecom.myshop.service.ProductServiceImpl;
import com.sofrecom.myshop.service.PromotionService;
import com.sofrecom.myshop.service.PromotionServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/shop-servlet.xml")
@ActiveProfiles("test")
public class PromotionServiceTest {

	@Autowired
	@InjectMocks
	PromotionService promotionService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFindPromotions() {
		List<SpecificPromotion> promotions = promotionService.findPromotions();

		assertNotNull(promotions);
	}

	@Configuration
	static class ContextConfiguration {

		// this bean will be injected into Test class
		@Bean
		public PromotionService promotionService() {
			PromotionService promotionService = new PromotionServiceImpl();
			// set properties, etc.
			return promotionService;
		}

		// this bean will be injected into Test class
		@Bean
		public ProductIService productService() {
			ProductIService productService = new ProductServiceImpl();
			// set properties, etc.
			return productService;
		}

	}

}
