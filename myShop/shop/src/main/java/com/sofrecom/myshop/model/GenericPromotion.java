package com.sofrecom.myshop.model;

import java.util.List;

public class GenericPromotion extends Promotion {

	private List<Long> productsIDs;
	private String percentage;

	/**
	 * 
	 */
	public GenericPromotion() {
		super();
	}

	/**
	 * @param productsIDs
	 * @param percentage
	 */
	public GenericPromotion(List<Long> productsIDs, String percentage) {
		super();
		this.productsIDs = productsIDs;
		this.percentage = percentage;
	}

	/**
	 * @return the productsIDs
	 */
	public List<Long> getProductsIDs() {
		return productsIDs;
	}

	/**
	 * @param productsIDs
	 *            the productsIDs to set
	 */
	public void setProductsIDs(List<Long> productsIDs) {
		this.productsIDs = productsIDs;
	}

	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericPromotion [productsIDs=" + productsIDs + ", percentage=" + percentage + "]";
	}

}
