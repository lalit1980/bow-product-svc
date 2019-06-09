package com.boozeonwheel.product.repository.sellercategory;

import java.util.List;

import com.boozeonwheel.product.domain.sellercategory.M_SellerCategory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface SellerCategoryRespositoryCustom {

	public List<M_SellerCategory> findBySellerCategoryId(int sellerCategoryId);

	public List<M_SellerCategory> findBySellerCategory(String sellerCategory);

	public List<M_SellerCategory> findByIsActive(Boolean isActive);

	public DeleteResult deleteProductTypeCategory(int sellerCategoryId);

	public void addAllProductTypeCategories(List<M_SellerCategory> sellerCategory);
	public void deleteAllProductTypeCategories();
	public UpdateResult updateProductCategoryType(M_SellerCategory sellerCategory);
	

	
}
