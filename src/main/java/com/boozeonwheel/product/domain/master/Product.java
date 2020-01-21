package com.boozeonwheel.product.domain.master;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Product")
public class Product {
	@Transient
    public static final String SEQUENCE_NAME = "product_sequence";
	@Id
    private long id;
    private String name;
    private String description;
    private String price;
    private String displayPrice;
    private String availableOn;
    private String slug;
    private String metaDescription;
    private String metaKeywords;
    private Integer shippingCategoryId;
    private List<Integer> taxonIds = new ArrayList<Integer>();
    private Integer totalOnHand;
    private String avgRating;
    private Integer reviewsCount;
    private String metaTitle;
    private Master master;
    private List<Master> variants = new ArrayList<Master>();
    private List<OptionType> optionTypes = new ArrayList<OptionType>();
    private List<String> productProperties = new ArrayList<String>();
   
    private Boolean hasVariants;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDisplayPrice() {
		return displayPrice;
	}
	public void setDisplayPrice(String displayPrice) {
		this.displayPrice = displayPrice;
	}
	public String getAvailableOn() {
		return availableOn;
	}
	public void setAvailableOn(String availableOn) {
		this.availableOn = availableOn;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	public Integer getShippingCategoryId() {
		return shippingCategoryId;
	}
	public void setShippingCategoryId(Integer shippingCategoryId) {
		this.shippingCategoryId = shippingCategoryId;
	}
	public List<Integer> getTaxonIds() {
		return taxonIds;
	}
	public void setTaxonIds(List<Integer> taxonIds) {
		this.taxonIds = taxonIds;
	}
	public Integer getTotalOnHand() {
		return totalOnHand;
	}
	public void setTotalOnHand(Integer totalOnHand) {
		this.totalOnHand = totalOnHand;
	}
	public String getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(String avgRating) {
		this.avgRating = avgRating;
	}
	public Integer getReviewsCount() {
		return reviewsCount;
	}
	public void setReviewsCount(Integer reviewsCount) {
		this.reviewsCount = reviewsCount;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public Master getMaster() {
		return master;
	}
	public void setMaster(Master master) {
		this.master = master;
	}
	public List<Master> getVariants() {
		return variants;
	}
	public void setVariants(List<Master> variants) {
		this.variants = variants;
	}
	public List<OptionType> getOptionTypes() {
		return optionTypes;
	}
	public void setOptionTypes(List<OptionType> optionTypes) {
		this.optionTypes = optionTypes;
	}
	public List<String> getProductProperties() {
		return productProperties;
	}
	public void setProductProperties(List<String> productProperties) {
		this.productProperties = productProperties;
	}
	
	public Boolean getHasVariants() {
		return hasVariants;
	}
	public void setHasVariants(Boolean hasVariants) {
		this.hasVariants = hasVariants;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", displayPrice=" + displayPrice + ", availableOn=" + availableOn + ", slug=" + slug
				+ ", metaDescription=" + metaDescription + ", metaKeywords=" + metaKeywords + ", shippingCategoryId="
				+ shippingCategoryId + ", taxonIds=" + taxonIds + ", totalOnHand=" + totalOnHand + ", avgRating="
				+ avgRating + ", reviewsCount=" + reviewsCount + ", metaTitle=" + metaTitle + ", master=" + master
				+ ", variants=" + variants + ", optionTypes=" + optionTypes + ", productProperties=" + productProperties
				+ ", hasVariants=" + hasVariants + "]";
	}
	
}
