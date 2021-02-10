package com.boozeonwheel.product.dto.file;

public class FileUploadResponse {
	private String imageUploadUrl;

	/**
	 * @param imageUploadUrl
	 */
	public FileUploadResponse(String imageUploadUrl) {
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
		return "FileUploadResponse [imageUploadUrl=" + imageUploadUrl + "]";
	}

}
