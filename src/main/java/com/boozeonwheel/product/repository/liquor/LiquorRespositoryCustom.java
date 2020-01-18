package com.boozeonwheel.product.repository.liquor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface LiquorRespositoryCustom{

	public M_LIQUOR findByLiquorCode(long liquorCode);

	public Page<M_LIQUOR> findByLiquorDescription(String liquorDescription,Pageable pageable);
	public List<M_LIQUOR> findBySupplier(String supplier);
	public List<M_LIQUOR> findByLiquorType(String liquorType);
	public List<M_LIQUOR> findByLiquorQuantity(String liquorQuanity);
	public List<M_LIQUOR> findByLiquorMeasurement(String liquorMeasurement);

	public List<M_LIQUOR> findByIsActive(Boolean isActive);

	public DeleteResult deleteLiquor(long liquorCode);

	public void addAllLiqor(List<M_LIQUOR> liquors);
	public void deleteAllLiquor();
	public UpdateResult updateLiquor(M_LIQUOR liquor);
	

	
}
