package com.boozeonwheel.product.repository.category;

import java.util.List;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductCategoryRespositoryCustom {

	public List<ProductCategory> findByProductTypeCategoryId(long productCategoryTypeId);

	public List<ProductCategory> findById(long id);
	public List<ProductCategory> findByParentIdAndCategoryId(long parentId,long categoryId);

	public List<ProductCategory> findByParentId(long parentCategoryId);


	public DeleteResult deleteProductTypeCategory(long productCategoryTypeId);

	public void addAllProductTypeCategories(List<ProductCategory> productCategoryType);
	public void deleteAllProductTypeCategories();
	public UpdateResult updateProductCategoryType(ProductCategory productCategoryType);
	public List<ProductCategory> findDistinctCategoyByParentId();
	

	
}
