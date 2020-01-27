package com.boozeonwheel.product.repository.category;

import java.util.List;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.boozeonwheel.product.exception.category.CategoryNotFoundException;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductCategoryRespositoryCustom {

	public ProductCategory findByCategory(long id) throws CategoryNotFoundException;
	public List<ProductCategory> findByParentIdAndCategoryId(long parentCategoryId,long id);
	public List<ProductCategory> findByParentId(long parentCategoryId);
	public DeleteResult deleteProductCategoryById(long id);
	public void addAllProductCategories(List<ProductCategory> productCategoryType);
	public void deleteAllProductTypeCategories();
	public UpdateResult updateProductCategoryType(ProductCategory productCategory);
	public List<ProductCategory> findDistinctCategoyByParentId();
	
	public UpdateResult updateProductCategoryType(long id, Boolean isMasterCategory,Boolean isMasterSubCategory);

	
}
