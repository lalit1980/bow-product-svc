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
import com.boozeonwheel.product.service.file.FileSequenceGeneratorService;
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

	@Autowired
	FileSequenceGeneratorService fileSeq;
	
	@GetMapping("/productcategorytype/v1.0/{id}")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public List<ProductCategory> get(@PathVariable("id") long id) {
		return productCategoryRepository.findByProductTypeCategoryId(id);
	}
	
	
	@GetMapping("/productcategorytype/v1.0/categoryId/{id}")
	@ApiOperation("Returns a specific Category ID. 404 if does not exist.")
	public List<ProductCategory> getCategoryId(@PathVariable("id") long id) {
		return productCategoryRepository.findById(id);
	}
	
	@GetMapping("/productcategorytype/v1.0/parentId/{parentId}/categoryId/{categoryId}")
	@ApiOperation("Returns a specific Category ID. 404 if does not exist.")
	public List<ProductCategory> getParentIdAndCategoryId(@PathVariable("parentId") long parentId,
			@PathVariable("categoryId") long categoryId) {
		return productCategoryRepository.findById(categoryId);
	}
	
	@GetMapping("/productcategorytype/v1.0/master/parentCategoryId/{parentCategoryId}")
	@ApiOperation("Returns a specific Parent Category ID. 404 if does not exist.")
	public List<ProductCategory> getByParentId(@PathVariable("parentCategoryId") long parentCategoryId) {
		return productCategoryRepository.findByParentId(parentCategoryId);
	}

	@PostMapping({ "/productcategorytype/v1.0" })
	@ApiOperation("Creates a new product category type.")
	public ProductCategory add(@RequestBody ProductCategory productCategory) {
		productCategory.setId(fileSeq.generateSequence(ProductCategory.SEQUENCE_NAME));
		return productCategoryRepository.save(productCategory);
	}

	@PutMapping("/productcategorytype/v1.0")
	@ApiOperation("Update a new product category type.")
	public UpdateResult update(@RequestBody ProductCategory productCategory){
		
		return productCategoryRepository.updateProductCategoryType(productCategory);
	}

	@DeleteMapping({ "/productcategorytype/v1.0/{id}" })
	@ApiOperation("Deletes a product category type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("id") long id) {
		return productCategoryRepository.deleteProductTypeCategory(id);
	}
	@ApiOperation("Returns list of all Product Category Type in the system.")
	@GetMapping("/productcategorytype/v1.0")
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}
}
