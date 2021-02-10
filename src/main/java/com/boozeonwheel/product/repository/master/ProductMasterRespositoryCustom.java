package com.boozeonwheel.product.repository.master;

import java.util.List;

import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.dto.master.MasterDTO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface ProductMasterRespositoryCustom {
	public Master findByProductMasterId(long id);
	public List<Master> findByProductCategoryId(long id);
	public DeleteResult deleteProductMaster(long id);
	public void addAllProductMasters(List<Master> masterList);
	public void deleteAllProductMaster();
	public UpdateResult updateProductMaster(MasterDTO master);
	public UpdateResult updateCategoryIdByMasterId(Integer id, Integer CategoryId);

}
