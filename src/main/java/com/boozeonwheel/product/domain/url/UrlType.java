package com.boozeonwheel.product.domain.url;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UrlType")
public class UrlType {
	
	private Integer urlId;
	/**
	 * 
	 */
	public UrlType() {
		super();
	}
	/**
	 * @param urlId
	 * @param urlType
	 */
	public UrlType(Integer urlId, String urlType) {
		super();
		this.urlId = urlId;
		this.urlType = urlType;
	}
	private String urlType;
	@Override
	public String toString() {
		return "UrlType [urlId=" + urlId + ", urlType=" + urlType + "]";
	}
	public Integer getUrlId() {
		return urlId;
	}
	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	
	
}
