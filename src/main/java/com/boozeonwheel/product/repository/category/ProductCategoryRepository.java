package com.boozeonwheel.product.repository.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.category.ProductCategory;


public interface ProductCategoryRepository extends MongoRepository<ProductCategory, Long>, ProductCategoryRespositoryCustom {
	Optional<List<ProductCategory>> findDistinctByChangesetId(Long changesetId);
	
}
