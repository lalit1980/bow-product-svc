package com.boozeonwheel.product.controller.sellercategory;

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

import com.boozeonwheel.product.domain.sellercategory.M_SellerCategory;
import com.boozeonwheel.product.repository.sellercategory.SellerCategoryRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Seller Category Type.")
public class SellerCategoryController {
	@Autowired
	SellerCategoryRepository sellerCategoryRepository;
	
	
	@GetMapping("/sellercategory/v1.0/{sellerCategoryId}")
	@ApiOperation("Returns a specific Seller Category Type by their identifier. 404 if does not exist.")
	public List<M_SellerCategory> get(@PathVariable("sellerCategoryId") int sellerCategoryId) {
		return sellerCategoryRepository.findBySellerCategoryId(sellerCategoryId);
	}
	
	
	@GetMapping("/sellercategory/v1.0/sellerCategory/{sellerCategory}")
	@ApiOperation("Returns a specific Seller Category Type. 404 if does not exist.")
	public List<M_SellerCategory> getProductCategoryByType(@PathVariable("sellerCategory") String sellerCategory) {
		return sellerCategoryRepository.findBySellerCategory(sellerCategory);
	}

	@PostMapping({ "/sellercategory/v1.0" })
	@ApiOperation("Creates a new seller category.")
	public M_SellerCategory add(@RequestBody M_SellerCategory sellerCategory) {
		sellerCategory.setLastUpdatedDate(new Date());
		return sellerCategoryRepository.save(sellerCategory);
	}

	@PutMapping("/sellercategory/v1.0")
	@ApiOperation("Update a new seller category type.")
	public UpdateResult update(@RequestBody M_SellerCategory productCategory){
		return sellerCategoryRepository.updateProductCategoryType(productCategory);
	}

	@DeleteMapping({ "/sellercategory/v1.0/{productCategoryTypeId}" })
	@ApiOperation("Deletes a seller category type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("productCategoryTypeId") int productCategoryTypeId) {
		return sellerCategoryRepository.deleteProductTypeCategory(productCategoryTypeId);
	}
	@ApiOperation("Returns list of all Seller Category Type in the system.")
	@GetMapping("/sellercategory/v1.0")
	public List<M_SellerCategory> findAll() {
		return sellerCategoryRepository.findAll();
	}
}
