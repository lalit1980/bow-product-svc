package com.boozeonwheel.product.controller.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boozeonwheel.product.dto.root.Root;
import com.boozeonwheel.product.dto.taxonomies.HomeResult;
import com.boozeonwheel.product.service.category.ProductCategoryBL;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Banners")
public class BannerController {
	@Autowired
	ProductCategoryBL productCategoryBL;
	
	@GetMapping("/v1/taxonomies")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<HomeResult> getBanner(@RequestParam("q[name_cont]") String bannerType,
			@RequestParam("set") String setType) {
		try {
			return new ResponseEntity<HomeResult>(productCategoryBL.getDummyBanner(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HomeResult>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/taxonomies/category")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<HomeResult> getCategory(@RequestParam("q[name_cont]") String bannerType) {
		try {
			return new ResponseEntity<HomeResult>(productCategoryBL.getProductCategory(Integer.parseInt(bannerType)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HomeResult>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/taxonomies/{parentId}/taxons/{categoryId}")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<Root> getProductByCategoryAndParentId(@PathVariable("parentId") String parentId,
			@PathVariable("categoryId") String categoryId) {
		try {
			return new ResponseEntity<Root>(productCategoryBL.getProductCategoryByParentIdAndCategoryId(Integer.parseInt(parentId),Integer.parseInt(categoryId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Root>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/v1/taxonomies/subcategory/{parentId}/taxons/{categoryId}")
	@ApiOperation("Returns a specific Product Category Type by their identifier. 404 if does not exist.")
	public ResponseEntity<Root> getProductSubCategor(@PathVariable("parentId") String parentId,
			@PathVariable("categoryId") String categoryId) {
		try {
			return new ResponseEntity<Root>(productCategoryBL.getProductSubCategoryByParentIdAndCategoryId(Integer.parseInt(parentId),Integer.parseInt(categoryId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Root>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
