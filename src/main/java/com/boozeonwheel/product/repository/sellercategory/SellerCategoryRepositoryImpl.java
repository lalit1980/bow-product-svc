package com.boozeonwheel.product.repository.sellercategory;

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

import com.boozeonwheel.product.domain.sellercategory.M_SellerCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class SellerCategoryRepositoryImpl implements SellerCategoryRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<M_SellerCategory> findBySellerCategoryId(int sellerCategoryId) {
		return mongoTemplate.find(new Query(Criteria.where("sellerCategoryId").is(sellerCategoryId)),
				M_SellerCategory.class);
	}

	@Override
	public List<M_SellerCategory> findBySellerCategory(String sellerCategory) {
		BasicQuery query = new BasicQuery("{\"sellerCategory\": {$regex : '" + sellerCategory + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_SellerCategory.class);
	}

	@Override
	public List<M_SellerCategory> findByIsActive(Boolean isActive) {
		return mongoTemplate.find(new Query(Criteria.where("isActive").is(isActive)), M_SellerCategory.class);
	}

	@Override
	public DeleteResult deleteProductTypeCategory(int sellerCategoryId) {
		Query query = new Query(Criteria.where("sellerCategoryId").is(sellerCategoryId));
		return mongoTemplate.remove(query, M_SellerCategory.class);
	}

	@Override
	public void addAllProductTypeCategories(List<M_SellerCategory> sellerCategory) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, M_SellerCategory.class);
		sellerCategory.forEach(sellerCategoryObj -> {
			bulkOperations.insert(sellerCategoryObj);
		});
		bulkOperations.execute();

	}

	@Override
	public void deleteAllProductTypeCategories() {
		mongoTemplate.remove(new Query(), "M_SellerCategory");

	}

	@Override
	public UpdateResult updateProductCategoryType(M_SellerCategory sellerCategory) {
		Query query = new Query(Criteria.where("productCategoryTypeId").is(sellerCategory.getSellerCategoryId()));
		Update update = new Update();
		update.set("productCategoryTypeId", sellerCategory.getSellerCategoryId());
		update.set("productCategoryType", sellerCategory.getSellerCategory());
		update.set("description", sellerCategory.getDescription());
		update.set("isActive", sellerCategory.getIsActive());
		update.set("lastUpdatedDate", new Date());
		update.set("imagePath", sellerCategory.getImagePath());

		return mongoTemplate.updateFirst(query, update, M_SellerCategory.class);
	}

}
