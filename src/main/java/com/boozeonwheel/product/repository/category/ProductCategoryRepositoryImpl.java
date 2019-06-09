package com.boozeonwheel.product.repository.category;

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

import com.boozeonwheel.product.domain.category.M_ProductCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductCategoryRepositoryImpl implements ProductCategoryRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<M_ProductCategory> findByProductTypeCategoryId(int productCategoryTypeId) {
		return mongoTemplate.find(new Query(Criteria.where("productCategoryTypeId").is(productCategoryTypeId)),
				M_ProductCategory.class);
	}

	@Override
	public List<M_ProductCategory> findByProductTypeCategory(String productCategoryType) {

		BasicQuery query = new BasicQuery("{\"productCategoryType\": {$regex : '" + productCategoryType + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_ProductCategory.class);

	}

	@Override
	public List<M_ProductCategory> findByIsActive(Boolean isActive) {
		return mongoTemplate.find(new Query(Criteria.where("isActive").is(isActive)), M_ProductCategory.class);
	}

	@Override
	public DeleteResult deleteProductTypeCategory(int productCategoryTypeId) {
		Query query = new Query(Criteria.where("productCategoryTypeId").is(productCategoryTypeId));
		return mongoTemplate.remove(query, M_ProductCategory.class);
	}

	@Override
	public void addAllProductTypeCategories(List<M_ProductCategory> productCategoryType) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, M_ProductCategory.class);
		productCategoryType.forEach(productCategoryTypeObj -> {
			bulkOperations.insert(productCategoryTypeObj);
		});
		bulkOperations.execute();
	}

	@Override
	public void deleteAllProductTypeCategories() {
		mongoTemplate.remove(new Query(), "M_ProductCategory");

	}

	@Override
	public UpdateResult updateProductCategoryType(M_ProductCategory productCategoryType) {
		Query query = new Query(Criteria.where("productCategoryTypeId").is(productCategoryType.getProductCategoryTypeId()));
		Update update = new Update();
		update.set("productCategoryTypeId", productCategoryType.getProductCategoryTypeId());
		update.set("productCategoryType", productCategoryType.getProductCategoryType());
		update.set("description", productCategoryType.getDescription());
		update.set("isActive", productCategoryType.getIsActive());
		update.set("lastUpdatedDate", new Date());
		update.set("imagePath", productCategoryType.getImagePath());
		update.set("updateBy", productCategoryType.getUpdateBy());
		return mongoTemplate.updateFirst(query, update, M_ProductCategory.class);
	}

}
