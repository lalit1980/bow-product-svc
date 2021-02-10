package com.boozeonwheel.product.dto.file;

public class ProductCategoryFileUploadResponse {
	private String imageUploadUrl;

	/**
	 * @param imageUploadUrl
	 */
	public ProductCategoryFileUploadResponse(String imageUploadUrl) {
		super();
		this.imageUploadUrl = imageUploadUrl;
	}

	public String getImageUploadUrl() {
		return imageUploadUrl;
	}

	public void setImageUploadUrl(String imageUploadUrl) {
		this.imageUploadUrl = imageUploadUrl;
	}

	@Override
	public String toString() {
		return "ProductCategoryFileUploadResponse [imageUploadUrl=" + imageUploadUrl + "]";
	}

}
