package com.boozeonwheel.product.domain.file;

public class UploadResponse {
    
    private String message;
    
    private  String title;
    
    
    private  String details;
   
    private  String fileName;
	
    private  long LIQUOR_CODE;
    
    private  String s3URL;
    
    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "UploadResponse [message=" + message + ", title=" + title + ", details=" + details + ", fileName="
				+ fileName + ", LIQUOR_CODE=" + LIQUOR_CODE + ", s3URL=" + s3URL + "]";
	}
	public long getLIQUOR_CODE() {
		return LIQUOR_CODE;
	}
	public void setLIQUOR_CODE(long lIQUOR_CODE) {
		LIQUOR_CODE = lIQUOR_CODE;
	}
	public String getS3URL() {
		return s3URL;
	}
	public void setS3URL(String s3url) {
		s3URL = s3url;
	}
	
}
