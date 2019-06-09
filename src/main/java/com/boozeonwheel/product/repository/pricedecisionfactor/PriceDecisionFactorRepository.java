package com.boozeonwheel.product.repository.pricedecisionfactor;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.pricedecisionfactor.M_PriceDecisionFactor;

public interface PriceDecisionFactorRepository extends MongoRepository<M_PriceDecisionFactor, Long>, PriceDecisionFactorRespositoryCustom {
	
}
