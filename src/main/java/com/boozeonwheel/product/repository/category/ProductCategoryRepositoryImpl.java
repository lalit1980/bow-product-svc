package com.boozeonwheel.product.repository.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.boozeonwheel.product.domain.category.EntityType;
import com.boozeonwheel.product.domain.category.ProductCategory;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductCategoryRepositoryImpl implements ProductCategoryRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	
	@Override
	public ProductCategory findByCategory(long id){
		try {
			Query query = new Query(Criteria.where("masterId").is(id));
			List<ProductCategory> categoryList=mongoTemplate.find(query, ProductCategory.class);
			if(CollectionUtils.isEmpty(categoryList)) {
				return null;
			}else {
				return categoryList.get(0);
			}

		} catch (Exception e) {
			throw null;
		}
	}

	@Override
	public List<ProductCategory> findByMasterId(long masterId) {
		Query query = new Query(Criteria.where("masterId").is(masterId));
		return mongoTemplate.find(query, ProductCategory.class);
	}

	@Override
	public List<ProductCategory> findByParentCategoryId(long parentCategoryId) {
		Query query = new Query(Criteria.where("parentCategoryId").is(parentCategoryId));
		return mongoTemplate.find(query, ProductCategory.class);
	}

	@Override
	public List<ProductCategory> findByType(String type) {
		Query query = new Query(Criteria.where("type").is(type));
		return mongoTemplate.find(query, ProductCategory.class);
	}

	@Override
	public List<ProductCategory> findByTest(long parentCategoryId, long masterId) {
		MongoCollection<Document> mongoCollection = mongoTemplate.getCollection("ProductCategory");
		BasicDBObject orQuery = new BasicDBObject();
		List<BasicDBObject> obj1 = new ArrayList<BasicDBObject>();
		obj1.add(new BasicDBObject("parentCategoryId", parentCategoryId));
		obj1.add(new BasicDBObject("masterId", masterId));
		orQuery.put("$or", obj1);
		FindIterable<Document> cursor = mongoCollection.find(orQuery);
		List<ProductCategory> list=new ArrayList<ProductCategory>();
		for (Document src : cursor) {
			ProductCategory dest=new ProductCategory();
			dest.setId(src.getLong("masterId"));
            dest.setMasterId(src.getLong("masterId"));
            dest.setType(EntityType.get(src.get("type").toString()));
			dest.setParentCategoryId(src.getLong("parentCategoryId"));
            dest.setParentId((List<Long>)src.get("parentId"));
            dest.setChangesetId(src.getLong("changesetId"));
            dest.setCategoryName(src.getString("categoryName"));
            dest.setChildren((List<ProductCategory>)src.get("children"));
            dest.setDescription(src.getString("description"));
            dest.setMetaDescription(src.getString("metaDescription"));
            dest.setMetaTitle(src.getString("metaTitle"));
            dest.setPermalink(src.getString("permalink"));
            dest.setTaxonomyId(src.getLong("taxonomyId"));
			list.add(dest);
		}
		return list;
	}

	@Override
	public List<ProductCategory> findByMasterIdIdIn(List<Long> parentCategoryId) {
		Query query = new Query(Criteria.where("masterId").in(parentCategoryId));
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
		update.set("masterId", productCategory.getMasterId());
		update.set("parentIds", productCategory.getParentId());
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

				productCategory = findByCategory(id);
				if(productCategory!=null) {
					hashSet.add(productCategory);
				}



		}
		return hashSet;
	}

	@Override
	public List<ProductCategory> findProductCategoriesByParentIds(List<Long> parentids) {
		Query query = new Query(Criteria.where("parentIds").is(parentids));
		return mongoTemplate.find(query, ProductCategory.class);
	}

	@Override
	public UpdateResult updateProductCategoryType(long id, Boolean isMasterCategory, Boolean isMasterSubCategory) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("isMasterCategory", isMasterCategory);
		update.set("isMasterSubCategory", isMasterSubCategory);
		return mongoTemplate.updateFirst(query, update, ProductCategory.class);
	}

	@Override
	public Optional<List<ProductCategory>> getSubTree(Long changesetId, Long masterId) throws Exception {
		final Criteria byMasterId = new Criteria("masterId").is(masterId);
		final Criteria byChangesetId = new Criteria("changesetId").is(changesetId);
		final MatchOperation matchStage = Aggregation.match(byChangesetId.andOperator(byMasterId));

		GraphLookupOperation graphLookupOperation = GraphLookupOperation.builder()
				.from("ProductCategory")
				.startWith("$masterId")
				.connectFrom("masterId")
				.connectTo("parentId")
				.restrict(new Criteria("changesetId").is(changesetId))
				.as("children");

		Aggregation aggregation = Aggregation.newAggregation(matchStage, graphLookupOperation);
		List<ProductCategory> results = mongoTemplate.aggregate(aggregation, "ProductCategory", ProductCategory.class).getMappedResults();
		return org.springframework.util.CollectionUtils.isEmpty(results) ? Optional.empty() : Optional.of(results);
	}


}
