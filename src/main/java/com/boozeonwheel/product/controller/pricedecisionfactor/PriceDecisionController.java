package com.boozeonwheel.product.controller.pricedecisionfactor;

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

import com.boozeonwheel.product.domain.pricedecisionfactor.M_PriceDecisionFactor;
import com.boozeonwheel.product.repository.pricedecisionfactor.PriceDecisionFactorRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Price Decision Factor.")
public class PriceDecisionController {
	@Autowired
	PriceDecisionFactorRepository priceDecisionFactorRepository;
	
	
	@GetMapping("/pricedecisionfactor/v1.0/{priceDecisionFactorId}")
	@ApiOperation("Returns a specific price decision factor by their identifier. 404 if does not exist.")
	public List<M_PriceDecisionFactor> get(@PathVariable("priceDecisionFactorId") int priceDecisionFactorId) {
		return priceDecisionFactorRepository.findByPriceDecisionFactorId(priceDecisionFactorId);
	}
	
	
	@GetMapping("/pricedecisionfactor/v1.0/priceDecisionFactor/{priceDecisionFactor}")
	@ApiOperation("Returns a specific price decision factor. 404 if does not exist.")
	public List<M_PriceDecisionFactor> getProductCategoryByType(@PathVariable("priceDecisionFactor") String priceDecisionFactor) {
		return priceDecisionFactorRepository.findByPriceDecisionFactor(priceDecisionFactor);
	}

	@PostMapping({ "/pricedecisionfactor/v1.0" })
	@ApiOperation("Creates a new product category type.")
	public M_PriceDecisionFactor add(@RequestBody M_PriceDecisionFactor priceDecisionFactors) {
		priceDecisionFactors.setLastUpdatedDate(new Date());
		return priceDecisionFactorRepository.save(priceDecisionFactors);
	}

	@PutMapping("/pricedecisionfactor/v1.0/{priceDecisionFactorId}/{priceDecisionFactor}/{description}/{updateBy}/{isActive}/{imagePath}")
	@ApiOperation("Update a new price decision factor.")
	public UpdateResult update(@PathVariable("priceDecisionFactorId") int priceDecisionFactorId,
			@PathVariable("priceDecisionFactor") String priceDecisionFactor,
			@PathVariable("description") String description,
			@PathVariable("updateBy") String updateBy,
			@PathVariable("isActive") Boolean isActive,
			@PathVariable("imagePath") String imagePath){
		return priceDecisionFactorRepository.updatePriceDecisionFactor(priceDecisionFactorId, priceDecisionFactor, description, updateBy, isActive, imagePath);
	}

	@DeleteMapping({ "/pricedecisionfactor/v1.0/{priceDecisionFactorId}" })
	@ApiOperation("Deletes a price decision factor from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("priceDecisionFactorId") int priceDecisionFactorId) {
		return priceDecisionFactorRepository.deletePriceDecisionFactor(priceDecisionFactorId);
	}
	@ApiOperation("Returns list of all price decision factor in the system.")
	@GetMapping("/pricedecisionfactor/v1.0")
	public List<M_PriceDecisionFactor> findAll() {
		return priceDecisionFactorRepository.findAll();
	}
}
