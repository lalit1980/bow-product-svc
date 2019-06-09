package com.boozeonwheel.product.repository.subcategory;

import java.util.List;

import com.boozeonwheel.product.domain.subcategory.M_ProductSubCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductSubCategoryRespositoryCustom {
	public List<M_ProductSubCategory> findByProductCategoryTypeId(int productCategoryTypeId);

	public List<M_ProductSubCategory> findByProductTypeSubCategoryId(int productSubCategoryTypeId);

	public List<M_ProductSubCategory> findByProductTypeSubCategory(String productSubCategoryType);

	public List<M_ProductSubCategory> findByIsActive(Boolean isActive);

	public DeleteResult deleteProductTypeSubCategory(int productSubCategoryTypeId);

	public void addAllProductTypeSubCategories(List<M_ProductSubCategory> productSubCategoryType);
	public void deleteAllProductTypeSubCategories();
	public UpdateResult updateProductTypeSubCategory(int productCategoryId,int productSubCategoryTypeId, String productSubCategoryType, String description, String updateBy,Boolean isActive, String imagePath);
	

	
}
