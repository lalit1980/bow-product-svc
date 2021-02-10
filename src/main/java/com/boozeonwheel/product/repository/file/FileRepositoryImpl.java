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

import com.boozeonwheel.product.domain.file.FileMetaData;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class FileRepositoryImpl implements FileRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<FileMetaData> findByFileId(long id) {
		return mongoTemplate.find(new Query(Criteria.where("id").is(id)), FileMetaData.class);
	}
	
	@Override
	public List<FileMetaData> findByProductId(long productCode) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productCode").in(productCode));
		return mongoTemplate.find(query, FileMetaData.class);
	}

	@Override
	public List<FileMetaData> findByFileName(String fileName) {
		BasicQuery query = new BasicQuery("{\"fileName\": {$regex : '" + fileName + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, FileMetaData.class);
	}

	@Override
	public List<FileMetaData> findByDetails(String details) {
		BasicQuery query = new BasicQuery("{\"details\": {$regex : '" + details + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, FileMetaData.class);
	}

	@Override
	public List<FileMetaData> findByIsActive(Boolean isActive) {
		return mongoTemplate.find(new Query(Criteria.where("isActive").is(isActive)), FileMetaData.class);
	}

	@Override
	public DeleteResult deleteFileByFileId(long fileId) {
		Query query = new Query(Criteria.where("fileId").is(fileId));
		return mongoTemplate.remove(query, FileMetaData.class);
	}

	@Override
	public DeleteResult deleteFileByLiquorCode(long LIQUOR_CODE) {
		Query query = new Query(Criteria.where("LIQUOR_CODE").is(LIQUOR_CODE));
		return mongoTemplate.remove(query, FileMetaData.class);
	}

	@Override
	public void addFiles(List<FileMetaData> filesMetadat) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, FileMetaData.class);
		filesMetadat.forEach(fileMetaDataObj -> {
			bulkOperations.insert(fileMetaDataObj);
		});
		bulkOperations.execute();
		
	}

	@Override
	public void deleteAllFiles() {
		mongoTemplate.remove(new Query(), "FileMetaData");
		
	}

	@Override
	public UpdateResult updateFileMetaData(FileMetaData fileMetaData, long id) {
		Query query=new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("fileId", fileMetaData.getFileId());
		update.set("productCode", fileMetaData.getProductCode());
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
		update.set("s3Path", fileMetaData.getS3Path());
		
		return mongoTemplate.updateFirst(query, update, FileMetaData.class);
	}

	
}
