package com.sofrecom.myshop.model;

import java.sql.Date;

public class SpecificPromotion {

	private Long id;
	private Double price;
	private Date startDate;
	private Date endDate;
	private String description;

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
	 * @param startDate
	 * @param endDate
	 * @param description
	 */
	public SpecificPromotion(Long id, Double price, Date startDate, Date endDate, String description) {
		super();
		this.id = id;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
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

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SpecificPromotion [id=" + id + ", price=" + price + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", description=" + description + "]";
	}

}
