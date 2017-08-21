package com.sofrecom.myshop.model;

import java.sql.Date;
import java.util.List;

public class Promotion {

	private List<Long> productsIDs;
	private Date startDate;
	private Date endDate;
	private String description;

	/**
	 * 
	 */
	public Promotion() {
		super();
	}

	/**
	 * @param productsIDs
	 * @param startDate
	 * @param endDate
	 * @param description
	 */
	public Promotion(List<Long> productsIDs, Date startDate, Date endDate, String description) {
		super();
		this.productsIDs = productsIDs;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
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
		return "Promotion [productsIDs=" + productsIDs + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", description=" + description + "]";
	}

}
