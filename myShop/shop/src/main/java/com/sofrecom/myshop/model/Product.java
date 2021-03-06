package com.sofrecom.myshop.model;

public class Product {

	protected Long id;
	protected Long ram;
	protected Long appareilPhoto;
	protected String url;
	protected String name;
	protected String brand;
	protected Double price;
	protected String description;
	protected Boolean isAvailable;
	protected String type;
	protected String segment;

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRam() {
		return ram;
	}

	public void setRam(Long ram) {
		this.ram = ram;
	}

	public Long getAppareilPhoto() {
		return appareilPhoto;
	}

	public void setAppareilPhoto(Long appareilPhoto) {
		this.appareilPhoto = appareilPhoto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", ram=" + ram + ", appareilPhoto=" + appareilPhoto + ", url=" + url + ", name="
				+ name + ", brand=" + brand + ", price=" + price + ", description=" + description + ", isAvailable="
				+ isAvailable + ", type=" + type + ", segmentAge=" + segment + "]";
	}

}
