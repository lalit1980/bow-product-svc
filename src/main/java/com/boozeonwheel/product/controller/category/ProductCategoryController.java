package com.boozeonwheel.product.controller.category;

import java.util.List;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.boozeonwheel.product.dto.master.CategoryDTO;
import com.boozeonwheel.product.exception.file.NotFoundException;
import com.boozeonwheel.product.repository.category.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boozeonwheel.product.service.category.ProductCategoryServiceImpl;
import com.boozeonwheel.product.service.file.FileSequenceGeneratorService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;



@RestController
@RequestMapping("/api")
public class ProductCategoryController {
	@Autowired
    ProductCategoryRepository productCategoryRepository;

	@Autowired
	FileSequenceGeneratorService fileSeq;
	@Autowired
	ProductCategoryServiceImpl productCategoryBL;
	
	@GetMapping("/productcategory/v1.0/{id}")
	public ResponseEntity<ProductCategory> findById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<ProductCategory>(productCategoryRepository.findByCategory(id), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<ProductCategory>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/productcategory/v1.0/distinct")
	public ResponseEntity<List<CategoryDTO>> getDistinctCategory() {
		try {
			return new ResponseEntity<List<CategoryDTO>>(productCategoryBL.findAllProductCategory(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/productcategory/v1.0/master/parentCategoryId/{parentCategoryId}")
	public List<ProductCategory> getByParentId(@PathVariable("parentCategoryId") long parentCategoryId) {
		return productCategoryRepository.findByParentCategoryId(parentCategoryId);
	}
	

	@PostMapping({ "/productcategory/v1.0" })
	public ResponseEntity<ProductCategory> add(@RequestBody ProductCategory productCategory) {
		try {
			
			return new ResponseEntity<ProductCategory>(productCategoryBL.saveProductCategory(productCategory), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ProductCategory>(HttpStatus.NOT_FOUND);
		}
		
		
		
	}

	@PutMapping("/productcategory/v1.0")
	public UpdateResult update(@RequestBody ProductCategory productCategory){
		
		return productCategoryRepository.updateProductCategoryType(productCategory);
	}

	@DeleteMapping({ "/productcategory/v1.0/{id}" })
	public DeleteResult delete(@PathVariable("id") long id) {
		return productCategoryRepository.deleteProductCategoryById(id);
	}
	@GetMapping("/productcategory/v1.0")
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}
	
	@PutMapping("/productcategory/v1.0/{id}/{isMasterCategory}/{isMasterSubCategory}")
	public UpdateResult update(@PathVariable("id") long id,
			@PathVariable("isMasterCategory") Boolean isMasterCategory,
			@PathVariable("isMasterSubCategory") Boolean isMasterSubCategory){
		
		return productCategoryRepository.updateProductCategoryType(id, isMasterCategory, isMasterSubCategory);
	}
}
