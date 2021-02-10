package com.boozeonwheel.product.controller.urltype;

import java.util.List;

import com.boozeonwheel.product.repository.url.UrlTypeRepository;
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
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UrlTypeController {
	
	@Autowired
	UrlTypeRepository urlTypeRepository;
	
	
	@PostMapping({ "/v1/urltype" })
	public ResponseEntity<UrlType> add(@RequestBody UrlType urlType) {
		
		try {
			return new ResponseEntity<UrlType>(urlTypeRepository.save(urlType), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UrlType>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/urltype")
	public ResponseEntity<List<UrlType>> geAUrlTypes() {
		try {
			return new ResponseEntity<List<UrlType>>(urlTypeRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UrlType>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping({ "/v1/urltype/productcode/{productCode}" })
	public DeleteResult deleteByProductCode(@PathVariable("productCode") long productCode) {
		return urlTypeRepository.deleteByProductCode(productCode);
	}
	
	@DeleteMapping({ "/v1/urltype/{urlId}" })
	public DeleteResult deleteByUrlId(@PathVariable("urlId") long urlId) {
		return urlTypeRepository.deleteByUrlId(urlId);
	}
	
	@PutMapping("/v1/urltype/{id}")
	public UpdateResult update(@RequestBody UrlType urlType) {
		return urlTypeRepository.updateUrlType(urlType);
	}
}
