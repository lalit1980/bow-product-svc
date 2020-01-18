package com.boozeonwheel.product.domain.category;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Document(collection = "ProductCategory")
@ApiModel(description = "Class representing a product category master data and tracked by the application.")
public class ProductCategory {

	
	@Indexed(name = "category_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the product category. No two persons can have the same id.", example = "1", required = true, position = 0)
	private int categoryId;
	@Indexed(name = "parent_category_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the product parent category. No two persons can have the same id.", example = "1", required = true, position = 0)
	private int parentCategoryId;
	private String categoryName;
	private String prettyName;
	private String permalink;
	private int taxonomyId;
	private String metaTitle;
	private String metaDescription;
	private String description;
	private String bannerImage;
	private String icon;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPrettyName() {
		return prettyName;
	}
	public void setPrettyName(String prettyName) {
		this.prettyName = prettyName;
	}
	public int getTaxonomyId() {
		return taxonomyId;
	}
	public void setTaxonomyId(int taxonomyId) {
		this.taxonomyId = taxonomyId;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBannerImage() {
		return bannerImage;
	}
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	@Override
	public String toString() {
		return "ProductCategory [categoryId=" + categoryId + ", parentCategoryId=" + parentCategoryId
				+ ", categoryName=" + categoryName + ", prettyName=" + prettyName + ", permalink=" + permalink
				+ ", taxonomyId=" + taxonomyId + ", metaTitle=" + metaTitle + ", metaDescription=" + metaDescription
				+ ", description=" + description + ", bannerImage=" + bannerImage + ", icon=" + icon + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bannerImage == null) ? 0 : bannerImage.hashCode());
		result = prime * result + categoryId;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((metaDescription == null) ? 0 : metaDescription.hashCode());
		result = prime * result + ((metaTitle == null) ? 0 : metaTitle.hashCode());
		result = prime * result + parentCategoryId;
		result = prime * result + ((permalink == null) ? 0 : permalink.hashCode());
		result = prime * result + ((prettyName == null) ? 0 : prettyName.hashCode());
		result = prime * result + taxonomyId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategory other = (ProductCategory) obj;
		if (bannerImage == null) {
			if (other.bannerImage != null)
				return false;
		} else if (!bannerImage.equals(other.bannerImage))
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (metaDescription == null) {
			if (other.metaDescription != null)
				return false;
		} else if (!metaDescription.equals(other.metaDescription))
			return false;
		if (metaTitle == null) {
			if (other.metaTitle != null)
				return false;
		} else if (!metaTitle.equals(other.metaTitle))
			return false;
		if (parentCategoryId != other.parentCategoryId)
			return false;
		if (permalink == null) {
			if (other.permalink != null)
				return false;
		} else if (!permalink.equals(other.permalink))
			return false;
		if (prettyName == null) {
			if (other.prettyName != null)
				return false;
		} else if (!prettyName.equals(other.prettyName))
			return false;
		if (taxonomyId != other.taxonomyId)
			return false;
		return true;
	}
	
}
