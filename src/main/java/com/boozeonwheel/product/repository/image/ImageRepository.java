package com.boozeonwheel.product.repository.image;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.category.ProductCategory;


public interface ImageRepository extends MongoRepository<ProductCategory, Long>, ImageRespositoryCustom {
	
}
