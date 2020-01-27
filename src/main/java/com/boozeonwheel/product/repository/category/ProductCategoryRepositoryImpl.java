package com.boozeonwheel.product.repository.category;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.boozeonwheel.product.exception.category.CategoryNotFoundException;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductCategoryRepositoryImpl implements ProductCategoryRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	
	@Override
	public ProductCategory findByCategory(long id) throws CategoryNotFoundException{
		try {
			Query query = new Query(Criteria.where("id").is(id));
			List<ProductCategory> categoryList=mongoTemplate.find(query, ProductCategory.class);
			if(CollectionUtils.isEmpty(categoryList)) {
				return null;
			}else {
				return categoryList.get(0);
			}
			
		} catch (Exception e) {
			throw new CategoryNotFoundException("Category ID="+id+" Not found in records", e);
		}
	}

	@Override
	public List<ProductCategory> findByParentId(long parentCategoryId) {
		Query query = new Query(Criteria.where("parentCategoryId").is(parentCategoryId));
		//query.with(new Sort(Sort.Direction.ASC, "id"));
		return mongoTemplate.find(query, ProductCategory.class);

	}

	@Override
	public List<ProductCategory> findByParentIdAndCategoryId(long parentId, long id) {
		Query query = new Query(Criteria.where("parentCategoryId").is(parentId).and("id").is(id));
		return mongoTemplate.find(query, ProductCategory.class);

	}

	@Override
	public DeleteResult deleteProductCategoryById(long id) {
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.remove(query, ProductCategory.class);
	}

	@Override
	public void addAllProductCategories(List<ProductCategory> productCategoryType) {
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
		update.set("isMasterCategory", productCategory.getIsMasterCategory());
		update.set("isMasterSubCategory", productCategory.getIsMasterSubCategory());
		return mongoTemplate.updateFirst(query, update, ProductCategory.class);
	}

	@Override
	public List<ProductCategory> findDistinctCategoyByParentId() {
		
		List<ProductCategory> hashSet = new ArrayList<ProductCategory>();

		MongoCollection<Document> mongoCollection = mongoTemplate.getCollection("ProductCategory");
		DistinctIterable<Long> distinctIterable = mongoCollection.distinct("parentCategoryId", Long.class);
		MongoCursor<Long> cursor = distinctIterable.iterator();
		while (cursor.hasNext()) {
			Long id = cursor.next();
			ProductCategory productCategory=null;
			try {
				productCategory = findByCategory(id);
				if(productCategory!=null) {
					hashSet.add(productCategory);
				}
				
			} catch (CategoryNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		return hashSet;
	}

	@Override
	public UpdateResult updateProductCategoryType(long id, Boolean isMasterCategory, Boolean isMasterSubCategory) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("isMasterCategory", isMasterCategory);
		update.set("isMasterSubCategory", isMasterSubCategory);
		return mongoTemplate.updateFirst(query, update, ProductCategory.class);
	}



}
