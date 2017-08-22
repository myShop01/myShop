package com.sofrecom.myshop.model;


public class SpecificPromotion extends Promotion {

	private Long id;
	private Double price;

	/**
	 * default constructor
	 */
	public SpecificPromotion() {
		super();
	}

	/**
	 * constructor using fields
	 * 
	 * @param id
	 * @param price
	 */
	public SpecificPromotion(Long id, Double price) {
		super();
		this.id = id;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SpecificPromotion [id=" + id + ", price=" + price + "]";
	}

}
