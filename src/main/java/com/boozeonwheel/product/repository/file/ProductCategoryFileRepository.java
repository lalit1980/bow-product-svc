package com.boozeonwheel.product.repository.file;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.file.ProductCategoryFileMetaData;

public interface ProductCategoryFileRepository extends MongoRepository<ProductCategoryFileMetaData, Long>, ProductCategoryFileRespositoryCustom {
	
}
