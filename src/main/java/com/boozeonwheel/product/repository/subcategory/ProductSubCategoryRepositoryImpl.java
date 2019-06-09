package com.boozeonwheel.product.repository.subcategory;

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

import com.boozeonwheel.product.domain.subcategory.M_ProductSubCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductSubCategoryRepositoryImpl implements ProductSubCategoryRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<M_ProductSubCategory> findByProductCategoryTypeId(int productCategoryTypeId) {
		return mongoTemplate.find(new Query(Criteria.where("productCategoryTypeId").is(productCategoryTypeId)),
				M_ProductSubCategory.class);
	}

	@Override
	public List<M_ProductSubCategory> findByProductTypeSubCategoryId(int productSubCategoryTypeId) {
		return mongoTemplate.find(new Query(Criteria.where("productSubCategoryTypeId").is(productSubCategoryTypeId)),
				M_ProductSubCategory.class);
	}

	@Override
	public List<M_ProductSubCategory> findByProductTypeSubCategory(String productSubCategoryType) {
		BasicQuery query = new BasicQuery("{\"productSubCategoryType\": {$regex : '" + productSubCategoryType + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_ProductSubCategory.class);
	}

	@Override
	public List<M_ProductSubCategory> findByIsActive(Boolean isActive) {
		return mongoTemplate.find(new Query(Criteria.where("isActive").is(isActive)), M_ProductSubCategory.class);
	}

	@Override
	public DeleteResult deleteProductTypeSubCategory(int productSubCategoryTypeId) {
		Query query = new Query(Criteria.where("productSubCategoryTypeId").is(productSubCategoryTypeId));
		return mongoTemplate.remove(query, M_ProductSubCategory.class);
	}

	@Override
	public void addAllProductTypeSubCategories(List<M_ProductSubCategory> productSubCategoryType) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, M_ProductSubCategory.class);
		productSubCategoryType.forEach(productSubCategoryTypeObj -> {
			bulkOperations.insert(productSubCategoryTypeObj);
		});
		bulkOperations.execute();
		
	}

	@Override
	public void deleteAllProductTypeSubCategories() {
		mongoTemplate.remove(new Query(), "M_ProductSubCategory");
		
	}

	@Override
	public UpdateResult updateProductTypeSubCategory(int productCategoryId, int productSubCategoryTypeId,
			String productSubCategoryType, String description, String updateBy, Boolean isActive, String imagePath) {
		Query query = new Query(Criteria.where("productSubCategoryTypeId").is(productSubCategoryTypeId));
		Update update = new Update();
		update.set("productCategoryId", productCategoryId);
		update.set("productSubCategoryType", productSubCategoryType);
		update.set("description", updateBy);
		update.set("isActive", isActive);
		update.set("lastUpdatedDate", new Date());
		update.set("imagePath", imagePath);
		return mongoTemplate.updateFirst(query, update, M_ProductSubCategory.class);
	}


}
