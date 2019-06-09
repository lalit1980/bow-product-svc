package com.boozeonwheel.product.repository.category;

import java.util.List;

import com.boozeonwheel.product.domain.category.M_ProductCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductCategoryRespositoryCustom {

	public List<M_ProductCategory> findByProductTypeCategoryId(int productCategoryTypeId);

	public List<M_ProductCategory> findByProductTypeCategory(String productTypeCategory);

	public List<M_ProductCategory> findByIsActive(Boolean isActive);

	public DeleteResult deleteProductTypeCategory(int productCategoryTypeId);

	public void addAllProductTypeCategories(List<M_ProductCategory> productCategoryType);
	public void deleteAllProductTypeCategories();
	public UpdateResult updateProductCategoryType(M_ProductCategory productCategoryType);
	

	
}
