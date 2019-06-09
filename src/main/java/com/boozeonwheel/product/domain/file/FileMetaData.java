package com.boozeonwheel.product.domain.file;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "Class representing a file's uploaded metadata and tracked by the application.")
@Document(collection = "FileMetaData")
public class FileMetaData {
	
	@Indexed(name = "file_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the uploaded file. No two file can have the same id.", example = "1", required = true, position = 0)
	private long fileId;
	
	
	@Indexed(name = "liquor_code_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the liquor. No two persons can have the same id.", example = "1", required = true, position = 0)
	private long LIQUOR_CODE;
    
	@Indexed(name = "file_name_index", expireAfterSeconds = 10)
	private String fileName;

	@Indexed(name = "content_type_index", expireAfterSeconds = 10)
    private String contentType;

    private long contentSize;

    private String title;

    private String details;

    private long createdAt;

    private String location;
    
    private String uploadedBy;
    
    private String s3Path;
    
    private long updaedAt;
    
    private Boolean isActive;

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getLIQUOR_CODE() {
		return LIQUOR_CODE;
	}

	public void setLIQUOR_CODE(long lIQUOR_CODE) {
		LIQUOR_CODE = lIQUOR_CODE;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getContentSize() {
		return contentSize;
	}

	public void setContentSize(long contentSize) {
		this.contentSize = contentSize;
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

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getS3Path() {
		return s3Path;
	}

	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
	}

	public long getUpdaedAt() {
		return updaedAt;
	}

	public void setUpdaedAt(long updaedAt) {
		this.updaedAt = updaedAt;
	}

	@Override
	public String toString() {
		return "FileMetaData [fileId=" + fileId + ", LIQUOR_CODE=" + LIQUOR_CODE + ", fileName=" + fileName
				+ ", contentType=" + contentType + ", contentSize=" + contentSize + ", title=" + title + ", details="
				+ details + ", createdAt=" + createdAt + ", location=" + location + ", uploadedBy=" + uploadedBy
				+ ", s3Path=" + s3Path + ", updaedAt=" + updaedAt + ", isActive=" + isActive + "]";
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	

}