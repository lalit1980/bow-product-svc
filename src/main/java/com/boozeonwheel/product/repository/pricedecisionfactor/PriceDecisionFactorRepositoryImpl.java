package com.boozeonwheel.product.repository.pricedecisionfactor;

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

import com.boozeonwheel.product.domain.pricedecisionfactor.M_PriceDecisionFactor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class PriceDecisionFactorRepositoryImpl implements PriceDecisionFactorRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<M_PriceDecisionFactor> findByPriceDecisionFactorId(int priceDecisionFactorId) {
		return mongoTemplate.find(new Query(Criteria.where("productCategoryTypeId").is(priceDecisionFactorId)),
				M_PriceDecisionFactor.class);
	}

	@Override
	public List<M_PriceDecisionFactor> findByPriceDecisionFactor(String priceDecisionFactor) {
		BasicQuery query = new BasicQuery("{\"priceDecisionFactor\": {$regex : '" + priceDecisionFactor + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_PriceDecisionFactor.class);
	}

	@Override
	public List<M_PriceDecisionFactor> findByIsActive(Boolean isActive) {
		return mongoTemplate.find(new Query(Criteria.where("isActive").is(isActive)), M_PriceDecisionFactor.class);
	}

	@Override
	public DeleteResult deletePriceDecisionFactor(int priceDecisionFactorId) {
		Query query = new Query(Criteria.where("priceDecisionFactorId").is(priceDecisionFactorId));
		return mongoTemplate.remove(query, M_PriceDecisionFactor.class);
	}

	@Override
	public void addAllPriceDecisionFactor(List<M_PriceDecisionFactor> priceDecisionFactors) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, M_PriceDecisionFactor.class);
		priceDecisionFactors.forEach(priceDecisionFactorObj -> {
			bulkOperations.insert(priceDecisionFactorObj);
		});
		bulkOperations.execute();
		
	}

	@Override
	public void deleteAllPriceDecisionFactors() {
		mongoTemplate.remove(new Query(), "M_PriceDecisionFactor");
		
	}

	@Override
	public UpdateResult updatePriceDecisionFactor(int priceDecisionFactorId, String priceDecisionFactor,
			String description, String updateBy, Boolean isActive, String imagePath) {
		Query query = new Query(Criteria.where("priceDecisionFactorId").is(priceDecisionFactorId));
		Update update = new Update();
		update.set("priceDecisionFactorId", priceDecisionFactorId);
		update.set("priceDecisionFactor", priceDecisionFactor);
		update.set("description", updateBy);
		update.set("isActive", isActive);
		update.set("lastUpdatedDate", new Date());
		update.set("imagePath", imagePath);
		return mongoTemplate.updateFirst(query, update, M_PriceDecisionFactor.class);
	}

	

}
