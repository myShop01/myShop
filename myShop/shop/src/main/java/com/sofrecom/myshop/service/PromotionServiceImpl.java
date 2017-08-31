package com.sofrecom.myshop.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.SpecificPromotion;

@Service
public class PromotionServiceImpl implements PromotionService {

	public static final String REST_URI_PREFIX = "http://localhost:3000";

	@Autowired
	ProductIService productService;
	

	@Override
	public List<SpecificPromotion> findPromotions() {
		RestTemplate restTemplate = new RestTemplate();
		List<SpecificPromotion> listPromotions = null;
		List<SpecificPromotion> auxList;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

		SpecificPromotion[] promotions = restTemplate.getForObject(REST_URI_PREFIX + "/promotions",
				SpecificPromotion[].class);
		
		if (promotions.length > 0) {
			listPromotions = new LinkedList<>(Arrays.asList(promotions));
			auxList = new LinkedList<>(Arrays.asList(promotions));
			for(SpecificPromotion promotion: auxList){
				LocalDate startDate = LocalDate.parse(promotion.getStartDate(), formatter);
				LocalDate endDate = LocalDate.parse(promotion.getEndDate(), formatter);
				
				if(!isWithinRange(startDate,endDate)){
					listPromotions.remove(promotion);
				}
				
			}
		}

		return listPromotions;
	}
	
	boolean isWithinRange(LocalDate startDate, LocalDate endDate) {
		   LocalDate currentDate = LocalDate.now();
		   return (startDate.isBefore(currentDate) || startDate.equals(currentDate)) && (endDate.isAfter(currentDate) || endDate.equals(currentDate));
		}

}
