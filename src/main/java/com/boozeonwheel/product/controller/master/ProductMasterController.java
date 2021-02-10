package com.boozeonwheel.product.controller.master;

import java.util.List;

import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.dto.master.MasterDTO;
import com.boozeonwheel.product.repository.master.ProductMasterRepository;
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

import com.boozeonwheel.product.service.category.ProductCategoryServiceImpl;
import com.boozeonwheel.product.service.file.FileSequenceGeneratorService;
import com.boozeonwheel.product.service.master.ProductMasterBL;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductMasterController {
	
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Autowired
	ProductMasterBL productMasterBL;
	
	@Autowired
	ProductCategoryServiceImpl productCategoryBL;
	
	@Autowired
	FileSequenceGeneratorService fileSeqGen;
	
	@PostMapping({ "/v1/master" })
	public ResponseEntity<Master> add(@RequestBody Master master) {
		
		try {
			master.setId(fileSeqGen.generateSequence(Master.SEQUENCE_NAME));
			return new ResponseEntity<Master>(productMasterRepository.save(master), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Master>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/master/category/{id}")
	public ResponseEntity<List<MasterDTO>> getCategory(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<List<MasterDTO>>(productMasterBL.getMasterDataByCategory(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<MasterDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/master/category/product/{id}")
	public ResponseEntity<MasterDTO> getMasterId(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<MasterDTO>(productMasterBL.getMasterDataByProductId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping({ "/v1/master/{id}" })
	public DeleteResult delete(@PathVariable("id") Integer id) {
		return productMasterRepository.deleteProductMaster(id);
	}
	
	@PutMapping("/v1/master/{id}")
	public UpdateResult update(@PathVariable("id") int id,
			@RequestBody MasterDTO master) {
		return productMasterRepository.updateProductMaster(master);
	}
	@PutMapping("/v1/master/{id}/{categoryId}")
	public UpdateResult updateCategoryById(@PathVariable("id") int id,
			@PathVariable("categoryId") int categoryId) {
		return productMasterRepository.updateCategoryIdByMasterId(id, categoryId);
	}
}
