package com.boozeonwheel.product.repository.file;

import java.util.List;

import com.boozeonwheel.product.domain.file.ProductCategoryFileMetaData;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductCategoryFileRespositoryCustom {

	public List<ProductCategoryFileMetaData> findByFileId(long id);
	public List<ProductCategoryFileMetaData> findByProductCategoryId(long productCategoryId);
	public List<ProductCategoryFileMetaData> findByFileName(String fileName);
	public DeleteResult deleteFileByFileId(long fileId);
	public void addFiles(List<ProductCategoryFileMetaData> filesMetadat);
	public void deleteAllFiles();
	public UpdateResult updateFileMetaData(ProductCategoryFileMetaData fileMetaData, long fileId);
	

	
}
