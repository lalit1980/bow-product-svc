package com.boozeonwheel.product.repository.url;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.url.UrlType;

public interface UrlTypeRepository extends MongoRepository<UrlType, Long>, UrlTypeRespositoryCustom {
	
}
