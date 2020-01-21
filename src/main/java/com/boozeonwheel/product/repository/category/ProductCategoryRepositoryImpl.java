package com.boozeonwheel.product.repository.category;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductCategoryRepositoryImpl implements ProductCategoryRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<ProductCategory> findByProductTypeCategoryId(long productCategoryTypeId) {
		return mongoTemplate.find(new Query(Criteria.where("id").is(productCategoryTypeId)),
				ProductCategory.class);
	}

	@Override
	public List<ProductCategory> findById(long id) {
		Query query = new Query(Criteria.where("id").is(id));
		// BasicQuery query = new BasicQuery("{\"categoryId\": {$regex : '" + categoryId
		// + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, ProductCategory.class);

	}

	@Override
	public List<ProductCategory> findByParentId(long parentCategoryId) {
		Query query = new Query(Criteria.where("parentCategoryId").is(parentCategoryId));
		return mongoTemplate.find(query, ProductCategory.class);

	}

	@Override
	public List<ProductCategory> findByParentIdAndCategoryId(long parentId, long id) {
		Query query = new Query(Criteria.where("parentCategoryId").is(parentId).and("id").is(id));
		// BasicQuery query = new BasicQuery("{\"categoryId\": {$regex : '" + categoryId
		// + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, ProductCategory.class);

	}

	@Override
	public DeleteResult deleteProductTypeCategory(long id) {
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.remove(query, ProductCategory.class);
	}

	@Override
	public void addAllProductTypeCategories(List<ProductCategory> productCategoryType) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, ProductCategory.class);
		productCategoryType.forEach(productCategoryTypeObj -> {
			bulkOperations.insert(productCategoryTypeObj);
		});
		bulkOperations.execute();
	}

	@Override
	public void deleteAllProductTypeCategories() {
		mongoTemplate.remove(new Query(), "ProductCategory");

	}

	@Override
	public UpdateResult updateProductCategoryType(ProductCategory productCategory) {
		Query query = new Query(Criteria.where("id").is(productCategory.getId()));
		Update update = new Update();
		update.set("parentCategoryId", productCategory.getParentCategoryId());
		update.set("categoryName", productCategory.getCategoryName());
		update.set("prettyName", productCategory.getPrettyName());
		update.set("taxonomyId", productCategory.getTaxonomyId());
		update.set("metaTitle", productCategory.getMetaTitle());
		update.set("metaDescription", productCategory.getMetaDescription());
		update.set("description", productCategory.getDescription());
		update.set("bannerImage", productCategory.getBannerImage());
		update.set("icon", productCategory.getIcon());
		return mongoTemplate.updateFirst(query, update, ProductCategory.class);
	}

	@Override
	public List<ProductCategory> findDistinctCategoyByParentId() {
		
		List<ProductCategory> hashSet = new ArrayList<ProductCategory>();

		MongoCollection<Document> mongoCollection = mongoTemplate.getCollection("ProductCategory");
		DistinctIterable<Integer> distinctIterable = mongoCollection.distinct("parentCategoryId", Integer.class);
		MongoCursor<Integer> cursor = distinctIterable.iterator();
		while (cursor.hasNext()) {
			Integer category = cursor.next();
			List<ProductCategory> list = findByProductTypeCategoryId(category);
			for (ProductCategory productCategory : list) {
				hashSet.add(productCategory);
			}
		}
		return hashSet;
	}

}
