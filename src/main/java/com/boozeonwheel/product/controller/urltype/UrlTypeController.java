package com.boozeonwheel.product.controller.urltype;

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

import com.boozeonwheel.product.domain.url.UrlType;
import com.boozeonwheel.product.repository.url.UrlTypeRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of UrlType.")
public class UrlTypeController {
	
	@Autowired
	UrlTypeRepository urlTypeRepository;
	
	
	@PostMapping({ "/v1/urltype" })
	@ApiOperation("Creates a new URL Type.")
	public ResponseEntity<UrlType> add(@RequestBody UrlType urlType) {
		
		try {
			return new ResponseEntity<UrlType>(urlTypeRepository.save(urlType), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UrlType>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/urltype")
	@ApiOperation("Returns a specific Url Type by product code. 404 if does not exist.")
	public ResponseEntity<List<UrlType>> geAUrlTypes() {
		try {
			return new ResponseEntity<List<UrlType>>(urlTypeRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UrlType>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping({ "/v1/urltype/productcode/{productCode}" })
	@ApiOperation("Deletes a URL type from the system. 404 if the person's identifier is not found.")
	public DeleteResult deleteByProductCode(@PathVariable("productCode") long productCode) {
		return urlTypeRepository.deleteByProductCode(productCode);
	}
	
	@DeleteMapping({ "/v1/urltype/{urlId}" })
	@ApiOperation("Deletes a URL type from the system. 404 if the person's identifier is not found.")
	public DeleteResult deleteByUrlId(@PathVariable("urlId") long urlId) {
		return urlTypeRepository.deleteByUrlId(urlId);
	}
	
	@PutMapping("/v1/urltype/{id}")
	@ApiOperation("Update URL Type data.")
	public UpdateResult update(@RequestBody UrlType urlType) {
		return urlTypeRepository.updateUrlType(urlType);
	}
}
