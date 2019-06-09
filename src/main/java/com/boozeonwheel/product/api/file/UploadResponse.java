package com.boozeonwheel.product.api.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;


@ApiModel(description = "A response of file upload operation")
public class UploadResponse {
    @ApiModelProperty(value = "Additional information about upload result", required = true, dataType = "string")
    private String message;
    @ApiModelProperty(value = "Title of uploaded file", required = true, dataType = "string")
    private  String title;
    @ApiModelProperty(value = "Details of uploaded file", required = true, dataType = "string")
    private  String details;
    @ApiModelProperty(value = "Uploaded file name", required = true, dataType = "string")
    private  String fileName;
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
				+ fileName + "]";
	}
	
}
