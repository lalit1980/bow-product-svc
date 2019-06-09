package com.boozeonwheel.product.controller.currencydominance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boozeonwheel.product.domain.currencydominance.M_CurrencyDominance;
import com.boozeonwheel.product.repository.currencydominance.CurrencyDominanceRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Currency Dominance.")
public class CurrencyDominanceController {
	@Autowired
	CurrencyDominanceRepository currencyDominanceRepository;

	@GetMapping("/currencydominance/v1.0/{currencyDominanceId}")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public List<M_CurrencyDominance> get(@PathVariable("currencyDominanceId") int currencyDominanceId) {
		return currencyDominanceRepository.findByCurrencyDominanceId(currencyDominanceId);
	}

	@GetMapping("/currencydominance/v1.0/currencyDominance/{currencyDominance}")
	@ApiOperation("Returns a specific currency dominance type. 404 if does not exist.")
	public List<M_CurrencyDominance> getCurrencyDominance(@PathVariable("currencyDominance") String currencyDominance) {
		return currencyDominanceRepository.findByCurrencyDominance(currencyDominance);
	}

	@PostMapping({ "/currencydominance/v1.0" })
	@ApiOperation("Creates a new currency dominance type.")
	public M_CurrencyDominance add(@RequestBody M_CurrencyDominance currencyDominance) {
		currencyDominance.setLastUpdatedDate(new Date());
		return currencyDominanceRepository.save(currencyDominance);
	}

	@PutMapping("/currencydominance/v1.0")
	@ApiOperation("Update a new product category type.")
	public UpdateResult update(@RequestBody M_CurrencyDominance currencyDominance) {
		return currencyDominanceRepository.updateCurrencyDominance(currencyDominance);
	}

	@DeleteMapping({ "/currencydominance/v1.0/{currencyDominanceId}" })
	@ApiOperation("Deletes a currency dominance type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("currencyDominanceId") int currencyDominanceId) {
		return currencyDominanceRepository.deleteCurrencyDominance(currencyDominanceId);
	}

	@ApiOperation("Returns list of all currency dominance Type in the system.")
	@GetMapping("/currencydominance/v1.0")
	public List<M_CurrencyDominance> findAll() {
		return currencyDominanceRepository.findAll();
	}
}
