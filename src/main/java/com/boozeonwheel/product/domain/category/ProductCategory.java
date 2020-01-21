package com.boozeonwheel.product.domain.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Document(collection = "ProductCategory")
@ApiModel(description = "Class representing a product category master data and tracked by the application.")
public class ProductCategory {

	@Transient
    public static final String SEQUENCE_NAME = "productcategory_sequence";
	//@Indexed(name = "category_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the product category. No two persons can have the same id.", example = "1", required = true, position = 0)
	
	@Id
	@Indexed(name = "id_index", direction = IndexDirection.ASCENDING)
	private long id;
	
	@ApiModelProperty(notes = "Unique identifier of the product parent category. No two persons can have the same id.", example = "1", required = true, position = 0)
	private long parentCategoryId;
	private String categoryName;
	private String prettyName;
	private String permalink;
	private long taxonomyId;
	private String metaTitle;
	private String metaDescription;
	private String description;
	private String bannerImage;
	private String icon;
	
	public long getParentCategoryId() {
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
	public long getTaxonomyId() {
		return taxonomyId;
	}
	public void setTaxonomyId(long taxonomyId) {
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", parentCategoryId=" + parentCategoryId + ", categoryName=" + categoryName
				+ ", prettyName=" + prettyName + ", permalink=" + permalink + ", taxonomyId=" + taxonomyId
				+ ", metaTitle=" + metaTitle + ", metaDescription=" + metaDescription + ", description=" + description
				+ ", bannerImage=" + bannerImage + ", icon=" + icon + "]";
	}
	
	
}
