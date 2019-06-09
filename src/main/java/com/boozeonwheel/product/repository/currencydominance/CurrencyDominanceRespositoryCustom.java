package com.boozeonwheel.product.repository.currencydominance;

import java.util.List;

import com.boozeonwheel.product.domain.currencydominance.M_CurrencyDominance;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface CurrencyDominanceRespositoryCustom {

	public List<M_CurrencyDominance> findByCurrencyDominanceId(int currencyDominanceId);

	public List<M_CurrencyDominance> findByCurrencyDominance(String currencyDominance);

	public List<M_CurrencyDominance> findByIsActive(Boolean isActive);

	public DeleteResult deleteCurrencyDominance(int currencyDominanceId);

	public void addAllCurrencyDominance(List<M_CurrencyDominance> currencyDominances);
	public void deleteAllCurrencyDominance();
	public UpdateResult updateCurrencyDominance(M_CurrencyDominance currencyDominance);
	

	
}
