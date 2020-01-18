package com.boozeonwheel.product.repository.master;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.master.Product;


public interface ProductRepository extends MongoRepository<Product, Integer>, ProductRespositoryCustom {
	
}
