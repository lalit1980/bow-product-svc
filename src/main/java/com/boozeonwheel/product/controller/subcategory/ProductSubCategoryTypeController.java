package com.boozeonwheel.product.controller.subcategory;

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

import com.boozeonwheel.product.domain.subcategory.M_ProductSubCategory;
import com.boozeonwheel.product.repository.subcategory.ProductSubCategoryRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Product Sub Category Type.")
public class ProductSubCategoryTypeController {
	@Autowired
	ProductSubCategoryRepository productSubCategoryRepository;
	
	
	@GetMapping("/productsubcategorytype/v1.0/{productSubCategoryTypeId}")
	@ApiOperation("Returns a specific Product Sub Category Type by their identifier. 404 if does not exist.")
	public List<M_ProductSubCategory> get(@PathVariable("productSubCategoryTypeId") int productSubCategoryTypeId) {
		return productSubCategoryRepository.findByProductTypeSubCategoryId(productSubCategoryTypeId);
	}
	
	
	@GetMapping("/productsubcategorytype/v1.0/productSubCategoryType/{productSubCategoryType}")
	@ApiOperation("Returns a specific Product Category Type. 404 if does not exist.")
	public List<M_ProductSubCategory> getProductSubCategoryByType(@PathVariable("productSubCategoryType") String productSubCategoryType) {
		return productSubCategoryRepository.findByProductTypeSubCategory(productSubCategoryType);
	}

	@PostMapping({ "/productsubcategorytype/v1.0" })
	@ApiOperation("Creates a new product category type.")
	public M_ProductSubCategory add(@RequestBody M_ProductSubCategory productSubCategory) {
		productSubCategory.setLastUpdatedDate(new Date());
		return productSubCategoryRepository.save(productSubCategory);
	}

	@PutMapping("/productsubcategorytype/v1.0/{productCategoryId}/{productSubCategoryTypeId}/{productSubCategoryType}/{description}/{updateBy}/{isActive}")
	@ApiOperation("Update a new product category type.")
	public UpdateResult update(@PathVariable("productCategoryId") int productCategoryId,
			@PathVariable("productSubCategoryTypeId") int productSubCategoryTypeId,
			@PathVariable("productSubCategoryType") String productSubCategoryType,
			@PathVariable("description") String description,
			@PathVariable("updateBy") String updateBy,
			@PathVariable("isActive") Boolean isActive,
			@PathVariable("imagePath") String imagePath){
		return productSubCategoryRepository.updateProductTypeSubCategory(productCategoryId, productSubCategoryTypeId,productSubCategoryType, description, updateBy,isActive,imagePath);
	}

	@DeleteMapping({ "/productsubcategorytype/v1.0/{productSubCategoryTypeId}" })
	@ApiOperation("Deletes a product category type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("productSubCategoryTypeId") int productSubCategoryTypeId) {
		return productSubCategoryRepository.deleteProductTypeSubCategory(productSubCategoryTypeId);
	}
	@ApiOperation("Returns list of all Product Category Type in the system.")
	@GetMapping("/productsubcategorytype/v1.0")
	public List<M_ProductSubCategory> findAll() {
		return productSubCategoryRepository.findAll();
	}
}
