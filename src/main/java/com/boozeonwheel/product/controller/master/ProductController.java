package com.boozeonwheel.product.controller.master;

import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.domain.master.Product;
import com.boozeonwheel.product.repository.master.ProductRepository;
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

import com.boozeonwheel.product.service.master.ProductMasterBL;
import com.mongodb.client.result.DeleteResult;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductRepository productMasterRepository;
	
	
	@Autowired
	ProductMasterBL productMasterBL;
	
	@PostMapping({ "/v1/taxons/products" })
	public ResponseEntity<Product> add(@RequestBody Product master) {
		
		try {
			return new ResponseEntity<Product>(productMasterRepository.save(master), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/taxons/products")
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
	public DeleteResult delete(@PathVariable("id") Integer id) {
		return productMasterRepository.deleteProductMaster(id);
	}
	
	@PostMapping({ "/v1/taxons/products/insertproductmaster" })
	public void add(@RequestBody Master master) {
		
		try {
			productMasterBL.mapLiquorToProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
