package com.sofrecom.myshop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.model.SpecificPromotion;

public class PromotionServiceImpl implements PromotionService {

	public static final String REST_URI_PREFIX = "http://localhost:3000";

	@Autowired
	ProductIService productService;

	@Override
	public List<SpecificPromotion> findPromotions() {
		// TODO: add test method
		RestTemplate restTemplate = new RestTemplate();
		List<SpecificPromotion> listPromotions = new ArrayList<>();

		SpecificPromotion[] promotions = restTemplate.getForObject(REST_URI_PREFIX + "/promotions",
				SpecificPromotion[].class);
		if (promotions.length > 0) {
			listPromotions = Arrays.asList(promotions);
		}

		return listPromotions;
	}

	@Override
	public List<Product> findProductsPromotions() {
		// TODO: add test method
		List<Long> ids = this.findPromotions().stream().map(SpecificPromotion :: getId).collect(Collectors.toList());
		return productService.findByIds(ids);
	}

}
