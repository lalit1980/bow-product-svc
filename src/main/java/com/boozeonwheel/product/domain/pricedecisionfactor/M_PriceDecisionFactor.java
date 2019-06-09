package com.boozeonwheel.product.domain.pricedecisionfactor;

import java.util.Date;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "M_PriceDecisionFactor")
@ApiModel(description = "Class representing a product price decision factor master data and tracked by the application.")
public class M_PriceDecisionFactor {

	@Indexed(name = "price_decision_factor_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the product price decision factor. No two persons can have the same id.", example = "1", required = true, position = 0)
	private int priceDecisionFactorId;
	
	@Indexed(name = "price_decision_factor_index", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Product price decision factor type will be unique", example = "KG/Liter/CM", required = true, position = 0)
	private String priceDecisionFactor;
	
	@ApiModelProperty(notes = "Product price decision factor description", example = "Detail description of particular product price decision factor", required = true, position = 0)
	private String description;
	
	@ApiModelProperty(notes = "Product price decision factor updated by can be a person who updated last", example = "System User", required = true, position = 0)
	private String updateBy;
	
	@ApiModelProperty(notes = "Product price decision factor is Active?", example = "true/false", required = true, position = 0)
	private Boolean isActive;
	
	//@ApiModelProperty(notes = "Product category type last updated date will be current date and will be captured by system", example = "26-Apr-2019 12:11:30 IST", required = true, position = 0)
	private Date lastUpdatedDate;
	@ApiModelProperty(notes = "Image path of Price Decision Factor", example = "http://www.example.com/image.jpeg", required = true, position = 0)
	private String imagePath;
	public int getPriceDecisionFactorId() {
		return priceDecisionFactorId;
	}
	public void setPriceDecisionFactorId(int priceDecisionFactorId) {
		this.priceDecisionFactorId = priceDecisionFactorId;
	}
	public String getPriceDecisionFactor() {
		return priceDecisionFactor;
	}
	public void setPriceDecisionFactor(String priceDecisionFactor) {
		this.priceDecisionFactor = priceDecisionFactor;
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
		return "M_PriceDecisionFactor [priceDecisionFactorId=" + priceDecisionFactorId + ", priceDecisionFactor="
				+ priceDecisionFactor + ", description=" + description + ", updateBy=" + updateBy + ", isActive="
				+ isActive + ", lastUpdatedDate=" + lastUpdatedDate + ", imagePath=" + imagePath + "]";
	}
}
