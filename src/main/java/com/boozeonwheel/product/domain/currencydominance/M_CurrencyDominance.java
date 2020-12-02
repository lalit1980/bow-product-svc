package com.boozeonwheel.product.domain.currencydominance;

import java.util.Date;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "M_CurrencyDominance")
@ApiModel(description = "Class representing a currency dominance master data and tracked by the application.")
public class M_CurrencyDominance {
	
	@Indexed(name = "currency_dominance_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the currency dominance. No two persons can have the same id.", example = "1", required = true, position = 0)
	private int currencyDominanceId;
	
	@Indexed(name = "currency_Dominance_index", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Currency dominance type will be unique", example = "USD", required = true, position = 0)
	private String currencyDominance;
	
	@ApiModelProperty(notes = "Currency dominance description", example = "Detail description of particular currency dominance", required = true, position = 0)
	private String description;
	
	@ApiModelProperty(notes = "Currency dominance type updated by can be a person who updated last", example = "System User", required = true, position = 0)
	private String updateBy;
	
	@ApiModelProperty(notes = "Currency dominance type is Active?", example = "true/false", required = true, position = 0)
	private Boolean isActive;
	
	private Date lastUpdatedDate;
	
	private String imagePath;

	public int getCurrencyDominanceId() {
		return currencyDominanceId;
	}

	public void setCurrencyDominanceId(int currencyDominanceId) {
		this.currencyDominanceId = currencyDominanceId;
	}

	public String getCurrencyDominance() {
		return currencyDominance;
	}

	public void setCurrencyDominance(String currencyDominance) {
		this.currencyDominance = currencyDominance;
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
		return "M_CurrencyDominance [currencyDominanceId=" + currencyDominanceId + ", currencyDominance="
				+ currencyDominance + ", description=" + description + ", updateBy=" + updateBy + ", isActive="
				+ isActive + ", lastUpdatedDate=" + lastUpdatedDate + ", imagePath=" + imagePath + "]";
	}

}
