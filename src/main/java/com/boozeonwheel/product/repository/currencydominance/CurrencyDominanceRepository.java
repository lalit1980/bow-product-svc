package com.boozeonwheel.product.repository.currencydominance;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.currencydominance.M_CurrencyDominance;

public interface CurrencyDominanceRepository extends MongoRepository<M_CurrencyDominance, Long>, CurrencyDominanceRespositoryCustom {
	
}
