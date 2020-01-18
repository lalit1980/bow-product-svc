package com.boozeonwheel.product.controller.category;

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

import com.boozeonwheel.product.domain.category.ProductCategory;
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
	
	
	@GetMapping("/productcategorytype/v1.0/{categoryId}")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public List<ProductCategory> get(@PathVariable("categoryId") int productCategoryTypeId) {
		return productCategoryRepository.findByProductTypeCategoryId(productCategoryTypeId);
	}
	
	
	@GetMapping("/productcategorytype/v1.0/categoryId/{categoryId}")
	@ApiOperation("Returns a specific Category ID. 404 if does not exist.")
	public List<ProductCategory> getCategoryId(@PathVariable("categoryId") int categoryId) {
		return productCategoryRepository.findByCategoryId(categoryId);
	}
	
	@GetMapping("/productcategorytype/v1.0/parentId/{parentId}/categoryId/{categoryId}")
	@ApiOperation("Returns a specific Category ID. 404 if does not exist.")
	public List<ProductCategory> getParentIdAndCategoryId(@PathVariable("parentId") int parentId,
			@PathVariable("categoryId") int categoryId) {
		return productCategoryRepository.findByCategoryId(categoryId);
	}
	
	@GetMapping("/productcategorytype/v1.0/master/parentCategoryId/{parentCategoryId}")
	@ApiOperation("Returns a specific Parent Category ID. 404 if does not exist.")
	public List<ProductCategory> getByParentId(@PathVariable("parentCategoryId") int parentCategoryId) {
		return productCategoryRepository.findByParentId(parentCategoryId);
	}

	@PostMapping({ "/productcategorytype/v1.0" })
	@ApiOperation("Creates a new product category type.")
	public ProductCategory add(@RequestBody ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	@PutMapping("/productcategorytype/v1.0")
	@ApiOperation("Update a new product category type.")
	public UpdateResult update(@RequestBody ProductCategory productCategory){
		
		return productCategoryRepository.updateProductCategoryType(productCategory);
	}

	@DeleteMapping({ "/productcategorytype/v1.0/{categoryId}" })
	@ApiOperation("Deletes a product category type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("categoryId") int productCategoryTypeId) {
		return productCategoryRepository.deleteProductTypeCategory(productCategoryTypeId);
	}
	@ApiOperation("Returns list of all Product Category Type in the system.")
	@GetMapping("/productcategorytype/v1.0")
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}
}
