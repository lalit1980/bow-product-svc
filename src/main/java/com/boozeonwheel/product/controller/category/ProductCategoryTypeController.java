package com.boozeonwheel.product.controller.category;

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

import com.boozeonwheel.product.domain.category.M_ProductCategory;
import com.boozeonwheel.product.repository.category.ProductCategoryRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Product Category Type.")
public class ProductCategoryTypeController {
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	
	@GetMapping("/productcategorytype/v1.0/{productCategoryTypeId}")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public List<M_ProductCategory> get(@PathVariable("productCategoryTypeId") int productCategoryTypeId) {
		return productCategoryRepository.findByProductTypeCategoryId(productCategoryTypeId);
	}
	
	
	@GetMapping("/productcategorytype/v1.0/productCategoryType/{productCategoryType}")
	@ApiOperation("Returns a specific Product Category Type. 404 if does not exist.")
	public List<M_ProductCategory> getProductCategoryByType(@PathVariable("productCategoryType") String productCategoryType) {
		return productCategoryRepository.findByProductTypeCategory(productCategoryType);
	}

	@PostMapping({ "/productcategorytype/v1.0" })
	@ApiOperation("Creates a new product category type.")
	public M_ProductCategory add(@RequestBody M_ProductCategory productCategory) {
		productCategory.setLastUpdatedDate(new Date());
		return productCategoryRepository.save(productCategory);
	}

	@PutMapping("/productcategorytype/v1.0")
	@ApiOperation("Update a new product category type.")
	public UpdateResult update(@RequestBody M_ProductCategory productCategory){
		
		return productCategoryRepository.updateProductCategoryType(productCategory);
	}

	@DeleteMapping({ "/productcategorytype/v1.0/{productCategoryTypeId}" })
	@ApiOperation("Deletes a product category type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("productCategoryTypeId") int productCategoryTypeId) {
		return productCategoryRepository.deleteProductTypeCategory(productCategoryTypeId);
	}
	@ApiOperation("Returns list of all Product Category Type in the system.")
	@GetMapping("/productcategorytype/v1.0")
	public List<M_ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}
}
