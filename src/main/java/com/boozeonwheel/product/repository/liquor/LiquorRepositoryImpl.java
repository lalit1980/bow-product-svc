package com.boozeonwheel.product.repository.liquor;

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

import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class LiquorRepositoryImpl implements LiquorRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<M_LIQUOR> findByLiquorCode(long liquorCode) {
		List<M_LIQUOR> list1= mongoTemplate.find(new Query(Criteria.where("LIQUOR_CODE").is(liquorCode)), M_LIQUOR.class);
		return list1;
	}

	@Override
	public List<M_LIQUOR> findByLiquorDescription(String liquorDescription) {
		BasicQuery query = new BasicQuery("{\"LIQUOR_DESCRIPTION\": {$regex : '" + liquorDescription + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_LIQUOR.class);
	}

	@Override
	public List<M_LIQUOR> findBySupplier(String supplier) {
		BasicQuery query = new BasicQuery("{\"LIQUOR_SUPPLIER\": {$regex : '" + supplier + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_LIQUOR.class);
	}
	
	@Override
	public List<M_LIQUOR> findByLiquorQuantity(String liquorQuanity) {
		BasicQuery query = new BasicQuery("{\"QUANTITY\": {$regex : '" + liquorQuanity + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_LIQUOR.class);
	}

	@Override
	public List<M_LIQUOR> findByLiquorMeasurement(String liquorMeasurement) {
		BasicQuery query = new BasicQuery("{\"MEASUREMENT\": {$regex : '" + liquorMeasurement + "'} }");
		query.limit(10);
		return mongoTemplate.find(query, M_LIQUOR.class);
	}

	@Override
	public List<M_LIQUOR> findByLiquorType(String liquorType) {
		BasicQuery query = new BasicQuery("{\"LIQUOR_TYPE\": {$regex : '" + liquorType + "'} }");
		//query.limit(10);
		return mongoTemplate.find(query, M_LIQUOR.class);
	}

	@Override
	public List<M_LIQUOR> findByIsActive(Boolean isActive) {
		return mongoTemplate.find(new Query(Criteria.where("IS_ACTIVE").is(isActive)), M_LIQUOR.class);
	}

	@Override
	public DeleteResult deleteLiquor(int liquorCode) {
		Query query = new Query(Criteria.where("LIQUOR_CODE").is(liquorCode));
		return mongoTemplate.remove(query, M_LIQUOR.class);
	}

	@Override
	public void addAllLiqor(List<M_LIQUOR> liquors) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, M_LIQUOR.class);
		liquors.forEach(liquorObj -> {
			bulkOperations.insert(liquorObj);
		});
		bulkOperations.execute();
		
	}

	@Override
	public void deleteAllLiquor() {
		mongoTemplate.remove(new Query(), "M_LIQUOR");
		
	}

	@Override
	public UpdateResult updateLiquor(M_LIQUOR liquor, long LIQUOR_CODE) {
		Query query = new Query(Criteria.where("LIQUOR_CODE").is(LIQUOR_CODE));
		Update update = new Update();
		update.set("IMAGE_PATH", liquor.getIMAGE_PATH());
		update.set("IS_ACTIVE", liquor.getIS_ACTIVE());
		update.set("LIQUOR_CODE", liquor.getLIQUOR_CODE());
		update.set("LIQUOR_DESCRIPTION", liquor.getLIQUOR_DESCRIPTION());
		//update.set("LAST_UPDATED_DATE", new Date());
		update.set("LIQUOR_SUPPLIER", liquor.getLIQUOR_SUPPLIER());
		update.set("LIQUOR_TYPE", liquor.getLIQUOR_TYPE());
		update.set("QUANTITY", liquor.getQUANTITY());
		update.set("MEASUREMENT", liquor.getMEASUREMENT());
		update.set("UPDATED_BY", liquor.getUPDATED_BY());
		return mongoTemplate.updateFirst(query, update, M_LIQUOR.class);
	}

	

	
}
