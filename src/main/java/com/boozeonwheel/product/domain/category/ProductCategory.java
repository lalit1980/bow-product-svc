package com.boozeonwheel.product.domain.category;

import java.util.ArrayList;
import java.util.List;

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
	private Long id;
	
	@ApiModelProperty(notes = "Unique identifier of the product parent category. No two persons can have the same id.", example = "1", required = true, position = 0)
	private Long parentCategoryId;
	private String categoryName;
	private String prettyName;
	private String permalink;
	private long taxonomyId;
	private String metaTitle;
	private String metaDescription;
	private String description;
	private Boolean isMasterCategory;
	private Boolean isMasterSubCategory;
	private ProductCategory parentCategory;
	private List<ProductCategory> subcategories;
	public ProductCategory() {
        super();
        this.subcategories = new ArrayList<ProductCategory>();
    }
	public ProductCategory(Long childId, Long parentId) {
        this.id = childId;
        this.parentCategoryId = parentId;
        this.subcategories = new ArrayList<ProductCategory>();
    }
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
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
	
	public Boolean getIsMasterCategory() {
		return isMasterCategory;
	}
	public void setIsMasterCategory(Boolean isMasterCategory) {
		this.isMasterCategory = isMasterCategory;
	}
	public Boolean getIsMasterSubCategory() {
		return isMasterSubCategory;
	}
	public void setIsMasterSubCategory(Boolean isMasterSubCategory) {
		this.isMasterSubCategory = isMasterSubCategory;
	}
	public Long getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	public ProductCategory getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(ProductCategory parentCategory) {
		this.parentCategory = parentCategory;
	}
	public List<ProductCategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<ProductCategory> subcategories) {
		this.subcategories = subcategories;
	}
	public void addSubCategories(ProductCategory child) {
        if (!this.subcategories.contains(child) && child != null)
            this.subcategories.add(child);
    }
	@Override
    public String toString() {
        return "Node [id=" + id + ", parentId=" + parentCategoryId + ", value=" + categoryName + ", children="
                + subcategories + "]";
    }
}
