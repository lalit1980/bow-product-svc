package com.boozeonwheel.product.repository.category;

import java.util.List;
import java.util.Optional;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductCategoryRespositoryCustom {

	public ProductCategory findByCategory(long id);
	public List<ProductCategory> findByParentIdAndCategoryId(long parentCategoryId,long id);
	public List<ProductCategory> findByMasterId(long masterId);
	public List<ProductCategory> findByParentCategoryId(long parentCategoryId);
	public List<ProductCategory> findByType(String type);
	public List<ProductCategory> findByTest(long parentCategoryId,long masterId);
	public List<ProductCategory> findByMasterIdIdIn(List<Long> parentCategoryId);
	public DeleteResult deleteProductCategoryById(long id);
	public void addAllProductCategories(List<ProductCategory> productCategoryType);
	public void deleteAllProductTypeCategories();
	public UpdateResult updateProductCategoryType(ProductCategory productCategory);
	public List<ProductCategory> findDistinctCategoyByParentId();
	public List<ProductCategory> findProductCategoriesByParentIds(List<Long> parentids);
	
	public UpdateResult updateProductCategoryType(long id, Boolean isMasterCategory,Boolean isMasterSubCategory);

	Optional<List<ProductCategory>> getSubTree(Long changesetId, Long nodeId) throws Exception;
}
