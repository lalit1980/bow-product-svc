package com.boozeonwheel.product.repository.file;

import java.util.Date;
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
import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class FileRepositoryImpl implements FileRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<FileMetaData> findByFileId(long fileId) {
		return mongoTemplate.find(new Query(Criteria.where("fileId").is(fileId)), FileMetaData.class);
	}

	@Override
	public List<FileMetaData> findByLiquorCode(long LIQUOR_CODE) {
		return mongoTemplate.find(new Query(Criteria.where("LIQUOR_CODE").is(LIQUOR_CODE)), FileMetaData.class);
		
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
	public UpdateResult updateFileMetaData(FileMetaData fileMetaData, long fileId) {
		Query query=new Query(Criteria.where("fileId").is(fileId));
		Update update = new Update();
		update.set("fileId", fileMetaData.getFileId());
		update.set("LIQUOR_CODE", fileMetaData.getLIQUOR_CODE());
		update.set("fileName", fileMetaData.getFileName());
		update.set("contentType", fileMetaData.getContentType());
		update.set("contentSize", fileMetaData.getContentSize());
		update.set("title", fileMetaData.getTitle());
		update.set("title", fileMetaData.getDetails());
		update.set("location", fileMetaData.getLocation());
		update.set("uploadedBy", fileMetaData.getUploadedBy());
		update.set("uploadedBy", fileMetaData.getS3Path());
		update.set("isActive", fileMetaData.getIsActive());
		update.set("updaedAt", new Date().getTime());
		
		return mongoTemplate.updateFirst(query, update, FileMetaData.class);
	}

	
}
