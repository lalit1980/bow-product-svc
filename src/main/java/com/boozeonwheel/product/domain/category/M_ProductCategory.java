package com.boozeonwheel.product.domain.category;

import java.util.Date;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "M_ProductCategory")
@ApiModel(description = "Class representing a product category master data and tracked by the application.")
public class M_ProductCategory {

	
	@Indexed(name = "product_category_type_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the product category. No two persons can have the same id.", example = "1", required = true, position = 0)
	private int productCategoryTypeId;
	
	@Indexed(name = "product_category_type", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Product category type will be unique", example = "Electronics", required = true, position = 0)
	private String productCategoryType;
	
	@ApiModelProperty(notes = "Product category description", example = "Detail description of particular product type", required = true, position = 0)
	private String description;
	
	@ApiModelProperty(notes = "Product category type updated by can be a person who updated last", example = "System User", required = true, position = 0)
	private String updateBy;
	
	@ApiModelProperty(notes = "Product category type is Active?", example = "true/false", required = true, position = 0)
	private Boolean isActive;
	
	//@ApiModelProperty(notes = "Product category type last updated date will be current date and will be captured by system", example = "26-Apr-2019 12:11:30 IST", required = true, position = 0)
	private Date lastUpdatedDate;
	
	private String imagePath;

	public int getProductCategoryTypeId() {
		return productCategoryTypeId;
	}

	public void setProductCategoryTypeId(int productCategoryTypeId) {
		this.productCategoryTypeId = productCategoryTypeId;
	}

	public String getProductCategoryType() {
		return productCategoryType;
	}

	public void setProductCategoryType(String productCategoryType) {
		this.productCategoryType = productCategoryType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "M_ProductCategory [productCategoryTypeId=" + productCategoryTypeId + ", productCategoryType="
				+ productCategoryType + ", description=" + description + ", updateBy=" + updateBy + ", isActive="
				+ isActive + ", lastUpdatedDate=" + lastUpdatedDate + ", imagePath=" + imagePath + "]";
	}
	
	
	
}
