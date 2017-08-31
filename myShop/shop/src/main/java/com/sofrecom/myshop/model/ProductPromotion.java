package com.sofrecom.myshop.model;

public class ProductPromotion extends Product{
	
	private Double promotionPrice;
	private String promotionDescription;
	/**
	 * @return the promotionPrice
	 */
	public Double getPromotionPrice() {
		return promotionPrice;
	}
	/**
	 * @param promotionPrice the promotionPrice to set
	 */
	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	/**
	 * @return the promotionDescription
	 */
	public String getPromotionDescription() {
		return promotionDescription;
	}
	/**
	 * @param promotionDescription the promotionDescription to set
	 */
	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

}
