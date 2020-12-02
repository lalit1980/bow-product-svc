package com.boozeonwheel.product.dto.master;

import io.swagger.annotations.ApiModelProperty;

public class ProductCategoryDTO {
private long id;
	
	@ApiModelProperty(notes = "Unique identifier of the product parent category. No two persons can have the same id.", example = "1", required = true, position = 0)
	private long productCategoryId;
	private String categoryName;
	private String prettyName;
	private String permalink;
	private long taxonomyId;
	private String metaTitle;
	private String metaDescription;
	private String description;
	private Boolean isMasterCategory;
	private Boolean isMasterSubCategory;
}
