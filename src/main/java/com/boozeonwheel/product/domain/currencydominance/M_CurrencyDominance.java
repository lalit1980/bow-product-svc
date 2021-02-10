package com.boozeonwheel.product.domain.currencydominance;

import java.util.Date;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "M_CurrencyDominance")
public class M_CurrencyDominance {
	
	@Indexed(name = "currency_dominance_id_index", direction = IndexDirection.DESCENDING)
	private int currencyDominanceId;
	
	@Indexed(name = "currency_Dominance_index", expireAfterSeconds = 10)
	private String currencyDominance;
	
	private String description;
	
	private String updateBy;
	
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
