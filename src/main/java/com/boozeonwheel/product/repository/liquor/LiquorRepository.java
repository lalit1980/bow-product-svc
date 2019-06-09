package com.boozeonwheel.product.repository.liquor;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.liquor.M_LIQUOR;

public interface LiquorRepository extends MongoRepository<M_LIQUOR, Long>, LiquorRespositoryCustom {
	
}
