package com.boozeonwheel.product.repository.pricedecisionfactor;

import java.util.List;

import com.boozeonwheel.product.domain.pricedecisionfactor.M_PriceDecisionFactor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface PriceDecisionFactorRespositoryCustom {
	public List<M_PriceDecisionFactor> findByPriceDecisionFactorId(int priceDecisionFactorId);


	public List<M_PriceDecisionFactor> findByPriceDecisionFactor(String priceDecisionFactor);

	public List<M_PriceDecisionFactor> findByIsActive(Boolean isActive);

	public DeleteResult deletePriceDecisionFactor(int priceDecisionFactorId);

	public void addAllPriceDecisionFactor(List<M_PriceDecisionFactor> priceDecisionFactors);
	public void deleteAllPriceDecisionFactors();
	public UpdateResult updatePriceDecisionFactor(int priceDecisionFactorId, String priceDecisionFactor, String description, String updateBy,Boolean isActive, String imagePath);
	

	
}
