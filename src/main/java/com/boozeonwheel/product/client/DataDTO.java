package com.boozeonwheel.product.client;

import org.apache.poi.sl.usermodel.PictureData;

public class DataDTO {
	private String productCode;
	private String styleDescription;
	private double width;
	private double height;
	private double length;
	private String material;
	private double price;
	private double categoryId;
	/**
	 * @param productCode
	 * @param styleDescription
	 * @param width
	 * @param height
	 * @param length
	 * @param material
	 * @param price
	 * @param categoryId
	 * @param pictureData
	 */
	public DataDTO(String productCode, String styleDescription, double width, double height, double length,
			String material, double price, double categoryId, PictureData pictureData) {
		super();
		this.productCode = productCode;
		this.styleDescription = styleDescription;
		this.width = width;
		this.height = height;
		this.length = length;
		this.material = material;
		this.price = price;
		this.categoryId = categoryId;
		this.pictureData = pictureData;
	}
	private PictureData pictureData;
	/**
	 * 
	 */
	public DataDTO() {
		super();
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getStyleDescription() {
		return styleDescription;
	}
	public void setStyleDescription(String styleDescription) {
		this.styleDescription = styleDescription;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public PictureData getPictureData() {
		return pictureData;
	}
	public void setPictureData(PictureData pictureData) {
		this.pictureData = pictureData;
	}
	@Override
	public String toString() {
		return "DataDTO [productCode=" + productCode + ", styleDescription=" + styleDescription + ", width=" + width
				+ ", height=" + height + ", length=" + length + ", material=" + material + ", price=" + price
				+ ", categoryId=" + categoryId + ", pictureData=" + pictureData + "]";
	}
	public double getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(double categoryId) {
		this.categoryId = categoryId;
	}
	

}
