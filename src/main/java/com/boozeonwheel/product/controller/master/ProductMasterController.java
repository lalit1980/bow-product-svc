package com.boozeonwheel.product.controller.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boozeonwheel.product.domain.master.CategoryDTO;
import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.dto.master.MasterDTO;
import com.boozeonwheel.product.repository.master.ProductMasterRepository;
import com.boozeonwheel.product.service.category.ProductCategoryBL;
import com.boozeonwheel.product.service.file.FileSequenceGeneratorService;
import com.boozeonwheel.product.service.master.ProductMasterBL;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Master.")
public class ProductMasterController {
	
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Autowired
	ProductMasterBL productMasterBL;
	
	@Autowired
	ProductCategoryBL productCategoryBL;
	
	@Autowired
	FileSequenceGeneratorService fileSeqGen;
	
	@PostMapping({ "/v1/master" })
	@ApiOperation("Creates a new product master.")
	public ResponseEntity<Master> add(@RequestBody Master master) {
		
		try {
			master.setId(fileSeqGen.generateSequence(Master.SEQUENCE_NAME));
			return new ResponseEntity<Master>(productMasterRepository.save(master), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Master>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/master/category/{id}")
	@ApiOperation("Returns a specific Master Product by Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<List<MasterDTO>> getCategory(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<List<MasterDTO>>(productMasterBL.getMasterDataByCategory(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<MasterDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/v1/master/")
	@ApiOperation("Returns a specific Master Product by Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<List<CategoryDTO>> getDistinctCategory() {
		try {
			return new ResponseEntity<List<CategoryDTO>>(productCategoryBL.findAllProductCategory(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/master/category/product/{id}")
	@ApiOperation("Returns a specific Master Product by Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<MasterDTO> getMasterId(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<MasterDTO>(productMasterBL.getMasterDataByProductId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping({ "/v1/master/{id}" })
	@ApiOperation("Deletes a product category type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("id") Integer id) {
		return productMasterRepository.deleteProductMaster(id);
	}
	
	@PutMapping("/v1/master/{id}")
	@ApiOperation("Update master data.")
	public UpdateResult update(@PathVariable("id") int id,
			@RequestBody MasterDTO master) {
		return productMasterRepository.updateProductMaster(master);
	}
	@PutMapping("/v1/master/{id}/{categoryId}")
	@ApiOperation("Update category Id data.")
	public UpdateResult updateCategoryById(@PathVariable("id") int id,
			@PathVariable("categoryId") int categoryId) {
		return productMasterRepository.updateCategoryIdByMasterId(id, categoryId);
	}
}
