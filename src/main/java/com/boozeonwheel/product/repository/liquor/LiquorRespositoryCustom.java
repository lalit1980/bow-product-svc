package com.boozeonwheel.product.repository.liquor;

import java.util.List;

import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface LiquorRespositoryCustom {

	public List<M_LIQUOR> findByLiquorCode(long liquorCode);

	public List<M_LIQUOR> findByLiquorDescription(String liquorDescription);
	public List<M_LIQUOR> findBySupplier(String supplier);
	public List<M_LIQUOR> findByLiquorType(String liquorType);
	public List<M_LIQUOR> findByLiquorQuantity(String liquorQuanity);
	public List<M_LIQUOR> findByLiquorMeasurement(String liquorMeasurement);

	public List<M_LIQUOR> findByIsActive(Boolean isActive);

	public DeleteResult deleteLiquor(int liquorCode);

	public void addAllLiqor(List<M_LIQUOR> liquors);
	public void deleteAllLiquor();
	public UpdateResult updateLiquor(M_LIQUOR liquor, long LIQUOR_CODE);
	

	
}
