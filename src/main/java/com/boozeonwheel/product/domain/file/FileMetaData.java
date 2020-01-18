package com.boozeonwheel.product.domain.file;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a file's uploaded metadata and tracked by the application.")
@Document(collection = "FileMetaData")
public class FileMetaData {
	@Transient
    public static final String SEQUENCE_NAME = "filemetadata_sequence";
	
    @Id
    private long id;
    
	@Indexed(name = "file_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the uploaded file. No two file can have the same id.", example = "1", required = true, position = 0)
	private String fileId;
	
	@Indexed(name = "product_code_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the liquor. No two persons can have the same id.", example = "1", required = true, position = 0)
	private long productCode;
	
	private String type;
    
	@Indexed(name = "file_name_index", expireAfterSeconds = 10)
	private String fileName;

	@Indexed(name = "content_type_index", expireAfterSeconds = 10)
    private String contentType;

    private long contentSize;

    private String location;
    
    private Integer attachmentWidth;
    private Integer attachmentHeight;
    private Date attachmentUpdatedAt;
    private Integer viewableId;
    private String viewableType;
    private String alt;
    private Integer position;
    private Integer urlTypeId;
    private String urlType;
    private String s3Path;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public long getProductCode() {
		return productCode;
	}

	public void setProductCode(long productCode) {
		this.productCode = productCode;
	}

	public String getS3Path() {
		return s3Path;
	}

	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
	}

	public Integer getAttachmentWidth() {
		return attachmentWidth;
	}

	public void setAttachmentWidth(Integer attachmentWidth) {
		this.attachmentWidth = attachmentWidth;
	}

	public Integer getAttachmentHeight() {
		return attachmentHeight;
	}

	public void setAttachmentHeight(Integer attachmentHeight) {
		this.attachmentHeight = attachmentHeight;
	}

	public Date getAttachmentUpdatedAt() {
		return attachmentUpdatedAt;
	}

	public void setAttachmentUpdatedAt(Date attachmentUpdatedAt) {
		this.attachmentUpdatedAt = attachmentUpdatedAt;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public Integer getViewableId() {
		return viewableId;
	}

	public void setViewableId(Integer viewableId) {
		this.viewableId = viewableId;
	}

	@Override
	public String toString() {
		return "FileMetaData [id=" + id + ", fileId=" + fileId + ", productCode=" + productCode + ", type=" + type
				+ ", fileName=" + fileName + ", contentType=" + contentType + ", contentSize=" + contentSize
				+ ", location=" + location + ", attachmentWidth=" + attachmentWidth + ", attachmentHeight="
				+ attachmentHeight + ", attachmentUpdatedAt=" + attachmentUpdatedAt + ", viewableId=" + viewableId
				+ ", viewableType=" + viewableType + ", alt=" + alt + ", position=" + position + ", urlTypeId="
				+ urlTypeId + ", urlType=" + urlType + ", s3Path=" + s3Path + "]";
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getViewableType() {
		return viewableType;
	}

	public void setViewableType(String viewableType) {
		this.viewableType = viewableType;
	}

	public Integer getUrlTypeId() {
		return urlTypeId;
	}

	public void setUrlTypeId(Integer urlTypeId) {
		this.urlTypeId = urlTypeId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
    
    
}