package com.boozeonwheel.product.repository.category;

import java.util.HashSet;
import java.util.List;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductCategoryRespositoryCustom {

	public List<ProductCategory> findByProductTypeCategoryId(int productCategoryTypeId);

	public List<ProductCategory> findByCategoryId(int categoryId);
	public List<ProductCategory> findByParentIdAndCategoryId(int parentId,int categoryId);

	public List<ProductCategory> findByParentId(int parentCategoryId);


	public DeleteResult deleteProductTypeCategory(int productCategoryTypeId);

	public void addAllProductTypeCategories(List<ProductCategory> productCategoryType);
	public void deleteAllProductTypeCategories();
	public UpdateResult updateProductCategoryType(ProductCategory productCategoryType);
	public List<ProductCategory> findDistinctCategoyByParentId();
	

	
}
