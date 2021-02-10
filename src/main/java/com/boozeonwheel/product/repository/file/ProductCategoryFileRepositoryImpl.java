package com.boozeonwheel.product.repository.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.boozeonwheel.product.domain.file.ProductCategoryFileMetaData;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductCategoryFileRepositoryImpl implements ProductCategoryFileRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<ProductCategoryFileMetaData> findByFileId(long id) {
		return mongoTemplate.find(new Query(Criteria.where("id").is(id)), ProductCategoryFileMetaData.class);
	}
	

	@Override
	public List<ProductCategoryFileMetaData> findByFileName(String fileName) {
		BasicQuery query = new BasicQuery("{\"fileName\": {$regex : '" + fileName + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, ProductCategoryFileMetaData.class);
	}

	
	
	@Override
	public DeleteResult deleteFileByFileId(long fileId) {
		Query query = new Query(Criteria.where("fileId").is(fileId));
		return mongoTemplate.remove(query, ProductCategoryFileMetaData.class);
	}


	@Override
	public void addFiles(List<ProductCategoryFileMetaData> filesMetadat) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, ProductCategoryFileMetaData.class);
		filesMetadat.forEach(fileMetaDataObj -> {
			bulkOperations.insert(fileMetaDataObj);
		});
		bulkOperations.execute();
		
	}

	@Override
	public void deleteAllFiles() {
		mongoTemplate.remove(new Query(), "ProductCategoryFileMetaData");
		
	}

	@Override
	public UpdateResult updateFileMetaData(ProductCategoryFileMetaData fileMetaData, long id) {
		Query query=new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("fileId", fileMetaData.getFileId());
		update.set("type", fileMetaData.getType());
		update.set("fileName", fileMetaData.getFileName());
		update.set("contentType", fileMetaData.getContentType());
		update.set("contentSize", fileMetaData.getContentSize());
		update.set("location", fileMetaData.getLocation());
		update.set("attachmentWidth",fileMetaData.getAttachmentWidth());
		update.set("attachmentHeight", fileMetaData.getAttachmentHeight());
		update.set("attachmentUpdatedAt", fileMetaData.getAttachmentUpdatedAt());
		update.set("viewableId", fileMetaData.getViewableId());
		update.set("viewableType", fileMetaData.getViewableType());
		update.set("alt", fileMetaData.getAlt());
		update.set("position", fileMetaData.getPosition());
		update.set("urlTypeId", fileMetaData.getUrlTypeId());
		update.set("urlType", fileMetaData.getUrlType());
		update.set("productCategoryId", fileMetaData.getProductCategoryId());
		update.set("s3Path", fileMetaData.getS3Path());
		
		return mongoTemplate.updateFirst(query, update, ProductCategoryFileMetaData.class);
	}


	@Override
	public List<ProductCategoryFileMetaData> findByProductCategoryId(long categoryId) {
		return mongoTemplate.find(new Query(Criteria.where("productCategoryId").is(categoryId)), ProductCategoryFileMetaData.class);
	}

	
}
