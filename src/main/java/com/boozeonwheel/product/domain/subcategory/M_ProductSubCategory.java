package com.boozeonwheel.product.domain.subcategory;

import java.util.Date;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "M_ProductSubCategory")
@ApiModel(description = "Class representing a product sub category master data and tracked by the application.")
public class M_ProductSubCategory {
	
	@ApiModelProperty(notes = "Primary key of product category type. One product sub category can have the same id.", example = "1", required = true, position = 0)
	private int productCategoryId;
	
	@Indexed(name = "product_sub_category_type_id_index", direction = IndexDirection.DESCENDING)
	private int productSubCategoryTypeId;
	
	@Indexed(name = "product_category_type", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Product category type will be unique", example = "Electronics", required = true, position = 0)
	private String productSubCategoryType;
	private String description;
	private String updateBy;
	private Boolean isActive;
	private Date lastUpdatedDate;
	private String imagePath;
	public int getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public int getProductSubCategoryTypeId() {
		return productSubCategoryTypeId;
	}
	public void setProductSubCategoryTypeId(int productSubCategoryTypeId) {
		this.productSubCategoryTypeId = productSubCategoryTypeId;
	}
	public String getProductSubCategoryType() {
		return productSubCategoryType;
	}
	public void setProductSubCategoryType(String productSubCategoryType) {
		this.productSubCategoryType = productSubCategoryType;
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
		return "M_ProductSubCategory [productCategoryId=" + productCategoryId + ", productSubCategoryTypeId="
				+ productSubCategoryTypeId + ", productSubCategoryType=" + productSubCategoryType + ", description="
				+ description + ", updateBy=" + updateBy + ", isActive=" + isActive + ", lastUpdatedDate="
				+ lastUpdatedDate + ", imagePath=" + imagePath + "]";
	}
	
	
}
