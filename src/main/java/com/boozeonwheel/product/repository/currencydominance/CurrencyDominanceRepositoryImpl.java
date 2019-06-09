package com.boozeonwheel.product.repository.currencydominance;

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

import com.boozeonwheel.product.domain.currencydominance.M_CurrencyDominance;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class CurrencyDominanceRepositoryImpl implements CurrencyDominanceRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<M_CurrencyDominance> findByCurrencyDominanceId(int currencyDominanceId) {
		return mongoTemplate.find(new Query(Criteria.where("currencyDominanceId").is(currencyDominanceId)),
				M_CurrencyDominance.class);
	}

	@Override
	public List<M_CurrencyDominance> findByCurrencyDominance(String currencyDominance) {
		BasicQuery query = new BasicQuery("{\"currencyDominance\": {$regex : '" + currencyDominance + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_CurrencyDominance.class);
	}

	@Override
	public List<M_CurrencyDominance> findByIsActive(Boolean isActive) {
		return mongoTemplate.find(new Query(Criteria.where("isActive").is(isActive)), M_CurrencyDominance.class);
	}

	@Override
	public DeleteResult deleteCurrencyDominance(int currencyDominanceId) {
		Query query = new Query(Criteria.where("currencyDominanceId").is(currencyDominanceId));
		return mongoTemplate.remove(query, M_CurrencyDominance.class);
	}

	@Override
	public void addAllCurrencyDominance(List<M_CurrencyDominance> currencyDominances) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, M_CurrencyDominance.class);
		currencyDominances.forEach(currencyDominancesObj -> {
			bulkOperations.insert(currencyDominancesObj);
		});
		bulkOperations.execute();
		
	}

	@Override
	public void deleteAllCurrencyDominance() {
		mongoTemplate.remove(new Query(), "M_CurrencyDominance");
		
	}

	@Override
	public UpdateResult updateCurrencyDominance(M_CurrencyDominance currencyDominance) {
		Query query = new Query(Criteria.where("productCategoryTypeId").is(currencyDominance.getCurrencyDominanceId()));
		Update update = new Update();
		update.set("currencyDominance", currencyDominance.getCurrencyDominance());
		update.set("description", currencyDominance.getDescription());
		update.set("isActive", currencyDominance.getIsActive());
		update.set("lastUpdatedDate", new Date());
		update.set("imagePath", currencyDominance.getImagePath());
		
		return mongoTemplate.updateFirst(query, update, M_CurrencyDominance.class);
	}

	

}
