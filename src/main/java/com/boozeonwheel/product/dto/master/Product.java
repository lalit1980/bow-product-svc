
package com.boozeonwheel.product.dto.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "price",
    "display_price",
    "available_on",
    "slug",
    "meta_description",
    "meta_keywords",
    "shipping_category_id",
    "taxon_ids",
    "total_on_hand",
    "avg_rating",
    "reviews_count",
    "meta_title",
    "master",
    "variants",
    "option_types",
    "product_properties",
    "classifications",
    "has_variants"
})
public class Product {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private String price;
    @JsonProperty("display_price")
    private String displayPrice;
    @JsonProperty("available_on")
    private String availableOn;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("meta_description")
    private String metaDescription;
    @JsonProperty("meta_keywords")
    private String metaKeywords;
    @JsonProperty("shipping_category_id")
    private Integer shippingCategoryId;
    @JsonProperty("taxon_ids")
    private List<Integer> taxonIds = new ArrayList<Integer>();
    @JsonProperty("total_on_hand")
    private Integer totalOnHand;
    @JsonProperty("avg_rating")
    private String avgRating;
    @JsonProperty("reviews_count")
    private Integer reviewsCount;
    @JsonProperty("meta_title")
    private String metaTitle;
    @JsonProperty("master")
    private MasterDTO master;
    @JsonProperty("variants")
    private List<MasterDTO> variants = new ArrayList<MasterDTO>();
    @JsonProperty("option_types")
    private List<OptionType> optionTypes = new ArrayList<OptionType>();
    @JsonProperty("product_properties")
    private List<Object> productProperties = new ArrayList<Object>();
    @JsonProperty("classifications")
    private List<Classification> classifications = new ArrayList<Classification>();
    @JsonProperty("has_variants")
    private Boolean hasVariants;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Product withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Product withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    public Product withPrice(String price) {
        this.price = price;
        return this;
    }

    @JsonProperty("display_price")
    public String getDisplayPrice() {
        return displayPrice;
    }

    @JsonProperty("display_price")
    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public Product withDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
        return this;
    }

    @JsonProperty("available_on")
    public String getAvailableOn() {
        return availableOn;
    }

    @JsonProperty("available_on")
    public void setAvailableOn(String availableOn) {
        this.availableOn = availableOn;
    }

    public Product withAvailableOn(String availableOn) {
        this.availableOn = availableOn;
        return this;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Product withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    @JsonProperty("meta_description")
    public String getMetaDescription() {
        return metaDescription;
    }

    @JsonProperty("meta_description")
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Product withMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
        return this;
    }

    @JsonProperty("meta_keywords")
    public String getMetaKeywords() {
        return metaKeywords;
    }

    @JsonProperty("meta_keywords")
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public Product withMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
        return this;
    }

    @JsonProperty("shipping_category_id")
    public Integer getShippingCategoryId() {
        return shippingCategoryId;
    }

    @JsonProperty("shipping_category_id")
    public void setShippingCategoryId(Integer shippingCategoryId) {
        this.shippingCategoryId = shippingCategoryId;
    }

    public Product withShippingCategoryId(Integer shippingCategoryId) {
        this.shippingCategoryId = shippingCategoryId;
        return this;
    }

    @JsonProperty("taxon_ids")
    public List<Integer> getTaxonIds() {
        return taxonIds;
    }

    @JsonProperty("taxon_ids")
    public void setTaxonIds(List<Integer> taxonIds) {
        this.taxonIds = taxonIds;
    }

    public Product withTaxonIds(List<Integer> taxonIds) {
        this.taxonIds = taxonIds;
        return this;
    }

    @JsonProperty("total_on_hand")
    public Integer getTotalOnHand() {
        return totalOnHand;
    }

    @JsonProperty("total_on_hand")
    public void setTotalOnHand(Integer totalOnHand) {
        this.totalOnHand = totalOnHand;
    }

    public Product withTotalOnHand(Integer totalOnHand) {
        this.totalOnHand = totalOnHand;
        return this;
    }

    @JsonProperty("avg_rating")
    public String getAvgRating() {
        return avgRating;
    }

    @JsonProperty("avg_rating")
    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public Product withAvgRating(String avgRating) {
        this.avgRating = avgRating;
        return this;
    }

    @JsonProperty("reviews_count")
    public Integer getReviewsCount() {
        return reviewsCount;
    }

    @JsonProperty("reviews_count")
    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public Product withReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
        return this;
    }

    @JsonProperty("meta_title")
    public String getMetaTitle() {
        return metaTitle;
    }

    @JsonProperty("meta_title")
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public Product withMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
        return this;
    }

    @JsonProperty("master")
    public MasterDTO getMaster() {
        return master;
    }

    @JsonProperty("master")
    public void setMaster(MasterDTO master) {
        this.master = master;
    }

    public Product withMaster(MasterDTO master) {
        this.master = master;
        return this;
    }

    @JsonProperty("variants")
    public List<MasterDTO> getVariants() {
        return variants;
    }

    @JsonProperty("variants")
    public void setVariants(List<MasterDTO> variants) {
        this.variants = variants;
    }

    public Product withVariants(List<MasterDTO> variants) {
        this.variants = variants;
        return this;
    }

    @JsonProperty("option_types")
    public List<OptionType> getOptionTypes() {
        return optionTypes;
    }

    @JsonProperty("option_types")
    public void setOptionTypes(List<OptionType> optionTypes) {
        this.optionTypes = optionTypes;
    }

    public Product withOptionTypes(List<OptionType> optionTypes) {
        this.optionTypes = optionTypes;
        return this;
    }

    @JsonProperty("product_properties")
    public List<Object> getProductProperties() {
        return productProperties;
    }

    @JsonProperty("product_properties")
    public void setProductProperties(List<Object> productProperties) {
        this.productProperties = productProperties;
    }

    public Product withProductProperties(List<Object> productProperties) {
        this.productProperties = productProperties;
        return this;
    }

    @JsonProperty("classifications")
    public List<Classification> getClassifications() {
        return classifications;
    }

    @JsonProperty("classifications")
    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public Product withClassifications(List<Classification> classifications) {
        this.classifications = classifications;
        return this;
    }

    @JsonProperty("has_variants")
    public Boolean getHasVariants() {
        return hasVariants;
    }

    @JsonProperty("has_variants")
    public void setHasVariants(Boolean hasVariants) {
        this.hasVariants = hasVariants;
    }

    public Product withHasVariants(Boolean hasVariants) {
        this.hasVariants = hasVariants;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
