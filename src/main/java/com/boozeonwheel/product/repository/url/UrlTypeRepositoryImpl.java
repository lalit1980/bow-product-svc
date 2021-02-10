package com.boozeonwheel.product.repository.url;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.boozeonwheel.product.domain.url.UrlType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class UrlTypeRepositoryImpl implements UrlTypeRespositoryCustom{

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<UrlType> findByProductId(long urlId) {
		Query query = new Query(Criteria.where("urlId").is(urlId));
		List<UrlType> urlType=mongoTemplate.find(query, UrlType.class);
		return urlType;
	}

	@Override
	public UrlType findByUrlId(Integer urlId) {
		Query query = new Query(Criteria.where("urlId").is(urlId));
		UrlType urlType=mongoTemplate.find(query, UrlType.class).get(0);
		return urlType;
	}

	@Override
	public DeleteResult deleteByUrlId(long urlId) {
		Query query = new Query(Criteria.where("urlId").is(urlId));
		return mongoTemplate.remove(query, UrlType.class);
	}

	@Override
	public DeleteResult deleteByProductCode(long productCode) {
		Query query = new Query(Criteria.where("productCode").is(productCode));
		return mongoTemplate.remove(query, UrlType.class);
	}

	@Override
	public UpdateResult updateUrlType(UrlType urlType) {
		Query query = new Query(Criteria.where("urlId").is(urlType.getUrlId()));
		Update update = new Update();
		update.set("urlType", urlType.getUrlType());
		return mongoTemplate.updateFirst(query, update, UrlType.class);
	}
	
	
	 
}
