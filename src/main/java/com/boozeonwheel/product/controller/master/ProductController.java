package com.boozeonwheel.product.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.domain.master.Product;
import com.boozeonwheel.product.repository.master.ProductRepository;
import com.boozeonwheel.product.service.master.ProductMasterBL;
import com.mongodb.client.result.DeleteResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Product Master.")
public class ProductController {
	
	@Autowired
	ProductRepository productMasterRepository;
	
	
	@Autowired
	ProductMasterBL productMasterBL;
	
	@PostMapping({ "/v1/taxons/products" })
	@ApiOperation("Creates a new product master.")
	public ResponseEntity<Product> add(@RequestBody Product master) {
		
		try {
			return new ResponseEntity<Product>(productMasterRepository.save(master), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/taxons/products")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<Product> getCategory(@RequestParam("id") Integer id,
			@RequestParam("page") String page,
			@RequestParam("per_page") String perPage,
			@RequestParam("q[s]") String sortBy,
			@RequestParam("data_set") String dataSet) {
		try {
			return new ResponseEntity<Product>(productMasterRepository.findByProductMasterId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping({ "/v1/taxons/products/{id}" })
	@ApiOperation("Deletes a product category type from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("id") Integer id) {
		return productMasterRepository.deleteProductMaster(id);
	}
	
	@PostMapping({ "/v1/taxons/products/insertproductmaster" })
	@ApiOperation("Creates a new product master.")
	public void add(@RequestBody Master master) {
		
		try {
			productMasterBL.mapLiquorToProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
