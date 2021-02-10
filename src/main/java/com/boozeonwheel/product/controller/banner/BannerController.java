package com.boozeonwheel.product.controller.banner;

import com.boozeonwheel.product.dto.root.Root;
import com.boozeonwheel.product.dto.taxonomies.HomeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boozeonwheel.product.service.category.ProductCategoryServiceImpl;

@RestController
@RequestMapping("/api")
public class BannerController {
	@Autowired
	ProductCategoryServiceImpl productCategoryBL;

	@GetMapping("/v1/taxonomies")
	public ResponseEntity<HomeResult> getBanner(@RequestParam("q[name_cont]") String bannerType,
												@RequestParam("set") String setType) {
		try {
			System.out.println("Git hub change");
			return new ResponseEntity<HomeResult>(productCategoryBL.getDummyBanner(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HomeResult>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/taxonomies/category")
	public ResponseEntity<HomeResult> getCategory(@RequestParam("q[name_cont]") String bannerType) {
		try {
			return new ResponseEntity<HomeResult>(productCategoryBL.getProductCategory(Integer.parseInt(bannerType)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HomeResult>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/taxonomies/{parentId}/taxons/{categoryId}")
	public ResponseEntity<Root> getProductByCategoryAndParentId(@PathVariable("parentId") String parentId,
																@PathVariable("categoryId") String categoryId) {
		try {
			return new ResponseEntity<Root>(productCategoryBL.getProductCategoryByParentIdAndCategoryId(Integer.parseInt(parentId),Integer.parseInt(categoryId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Root>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/v1/taxonomies/subcategory/{parentId}/taxons/{categoryId}")
	public ResponseEntity<Root> getProductSubCategor(@PathVariable("parentId") String parentId,
			@PathVariable("categoryId") String categoryId) {
		try {
			return new ResponseEntity<Root>(productCategoryBL.getProductSubCategoryByParentIdAndCategoryId(Integer.parseInt(parentId),Integer.parseInt(categoryId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Root>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
