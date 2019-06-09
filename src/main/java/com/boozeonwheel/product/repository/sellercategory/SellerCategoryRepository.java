package com.boozeonwheel.product.repository.sellercategory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.sellercategory.M_SellerCategory;

public interface SellerCategoryRepository extends MongoRepository<M_SellerCategory, Long>, SellerCategoryRespositoryCustom {
	
}
