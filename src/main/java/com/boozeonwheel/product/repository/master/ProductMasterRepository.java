package com.boozeonwheel.product.repository.master;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.master.Master;

public interface ProductMasterRepository extends MongoRepository<Master, Integer>, ProductMasterRespositoryCustom {
	
}
