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
	private String fileId;
	
	
	@Indexed(name = "liquor_code_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the liquor. No two persons can have the same id.", example = "1", required = true, position = 0)
	private long LIQUOR_CODE;
    
	@Indexed(name = "file_name_index", expireAfterSeconds = 10)
	private String fileName;

	@Indexed(name = "content_type_index", expireAfterSeconds = 10)
    private String contentType;

    private long contentSize;

    private String location;
    
    private String s3Path;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getS3Path() {
		return s3Path;
	}

	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
	}

	@Override
	public String toString() {
		return "FileMetaData [fileId=" + fileId + ", LIQUOR_CODE=" + LIQUOR_CODE + ", fileName=" + fileName
				+ ", contentType=" + contentType + ", contentSize=" + contentSize + ", location=" + location
				+ ", s3Path=" + s3Path + "]";
	}
    
    
}