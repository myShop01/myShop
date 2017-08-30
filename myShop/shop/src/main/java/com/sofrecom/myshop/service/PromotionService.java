package com.sofrecom.myshop.service;

import java.util.List;

import com.sofrecom.myshop.model.Product;
import com.sofrecom.myshop.model.SpecificPromotion;

public interface PromotionService {
	List<SpecificPromotion> findPromotions();
	List<Product> findProductsPromotions();
}
