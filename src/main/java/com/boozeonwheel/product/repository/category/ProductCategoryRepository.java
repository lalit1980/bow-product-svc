package com.boozeonwheel.product.repository.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.category.M_ProductCategory;


public interface ProductCategoryRepository extends MongoRepository<M_ProductCategory, Long>, ProductCategoryRespositoryCustom {
	
}
