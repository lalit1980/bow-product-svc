package com.boozeonwheel.product.repository.file;

import java.util.List;

import com.boozeonwheel.product.domain.file.FileMetaData;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface FileRespositoryCustom {

	public List<FileMetaData> findByFileId(long fileId);
	public List<FileMetaData> findByLiquorCode(long LIQUOR_CODE);

	public List<FileMetaData> findByFileName(String fileName);
	public List<FileMetaData> findByDetails(String details);
	

	public List<FileMetaData> findByIsActive(Boolean isActive);

	public DeleteResult deleteFileByFileId(long fileId);
	public DeleteResult deleteFileByLiquorCode(long LIQUOR_CODE);

	public void addFiles(List<FileMetaData> filesMetadat);
	public void deleteAllFiles();
	public UpdateResult updateFileMetaData(FileMetaData fileMetaData, long fileId);
	

	
}
