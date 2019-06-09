package com.boozeonwheel.product.domain.sellercategory;

import java.util.Date;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "M_SellerCategory")
@ApiModel(description = "Class representing a seller category master data and tracked by the application.")
public class M_SellerCategory {

	
	@Indexed(name = "seller_category_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the seller category. No two seller can have the same id.", example = "1", required = true, position = 0)
	private int sellerCategoryId;
	
	@Indexed(name = "seller_category", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Seller category type will be unique", example = "Company/Retail Shop/Small scale bbusiness", required = true, position = 0)
	private String sellerCategory;
	
	@ApiModelProperty(notes = "Seller category description", example = "Detail description of particular Seller type", required = true, position = 0)
	private String description;
	
	@ApiModelProperty(notes = "Seller category type updated by can be a person who updated last", example = "System User", required = true, position = 0)
	private String updateBy;
	
	@ApiModelProperty(notes = "Seller category type is Active?", example = "true/false", required = true, position = 0)
	private Boolean isActive;
	
	private Date lastUpdatedDate;
	
	private String imagePath;

	public int getSellerCategoryId() {
		return sellerCategoryId;
	}

	public void setSellerCategoryId(int sellerCategoryId) {
		this.sellerCategoryId = sellerCategoryId;
	}

	public String getSellerCategory() {
		return sellerCategory;
	}

	public void setSellerCategory(String sellerCategory) {
		this.sellerCategory = sellerCategory;
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
		return "M_SellerCategory [sellerCategoryId=" + sellerCategoryId + ", sellerCategory=" + sellerCategory
				+ ", description=" + description + ", updateBy=" + updateBy + ", isActive=" + isActive
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", imagePath=" + imagePath + "]";
	}
}
