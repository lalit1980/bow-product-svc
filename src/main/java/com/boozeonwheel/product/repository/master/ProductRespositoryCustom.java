package com.boozeonwheel.product.repository.master;

import java.util.List;

import com.boozeonwheel.product.domain.master.Product;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductRespositoryCustom {

	public Product findByProductMasterId(Integer id);
	public DeleteResult deleteProductMaster(Integer id);
	public void addAllProductMasters(List<Product> masterList);
	public void deleteAllProductMaster();
	public UpdateResult updateProductMaster(Product master);
	

	
}
