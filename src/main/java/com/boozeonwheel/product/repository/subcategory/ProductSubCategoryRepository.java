package com.boozeonwheel.product.repository.subcategory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.subcategory.M_ProductSubCategory;

public interface ProductSubCategoryRepository extends MongoRepository<M_ProductSubCategory, Long>, ProductSubCategoryRespositoryCustom {
	
}
