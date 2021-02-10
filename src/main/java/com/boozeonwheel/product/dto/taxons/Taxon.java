
package com.boozeonwheel.product.dto.taxons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
@JsonPropertyOrder({
    "id",
    "name",
    "pretty_name",
    "permalink",
    "parent_id",
    "taxonomy_id",
    "meta_title",
    "meta_description",
    "description",
    "banner_image",
    "taxons",
    "icon"
})
public class Taxon {

    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pretty_name")
    private String pretty_name;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("parent_id")
    private long parent_id;
    @JsonProperty("taxonomy_id")
    private long taxonomy_id;
    @JsonProperty("meta_title")
    private String meta_title;
    @JsonProperty("meta_description")
    private String meta_description;
    @JsonProperty("description")
    private String description;
    @JsonProperty("banner_image")
    private Object banner_image;
    @JsonProperty("taxons")
    private List<Taxon> taxons = new ArrayList<Taxon>();
    @JsonProperty("icon")
    private String icon;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public Taxon withId(Integer id) {
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

    public Taxon withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("pretty_name")
    public String getPrettyName() {
        return pretty_name;
    }

    @JsonProperty("pretty_name")
    public void setPrettyName(String prettyName) {
        this.pretty_name = prettyName;
    }

    public Taxon withPrettyName(String prettyName) {
        this.pretty_name = prettyName;
        return this;
    }

    @JsonProperty("permalink")
    public String getPermalink() {
        return permalink;
    }

    @JsonProperty("permalink")
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Taxon withPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    @JsonProperty("parent_id")
    public long getParentId() {
        return parent_id;
    }

    @JsonProperty("parent_id")
    public void setParentId(long parentId) {
        this.parent_id = parentId;
    }

    public Taxon withParentId(long parentId) {
        this.parent_id = parentId;
        return this;
    }

    @JsonProperty("taxonomy_id")
    public long getTaxonomyId() {
        return taxonomy_id;
    }

    @JsonProperty("taxonomy_id")
    public void setTaxonomyId(long taxonomyId) {
        this.taxonomy_id = taxonomyId;
    }

    public Taxon withTaxonomyId(long taxonomyId) {
        this.taxonomy_id = taxonomyId;
        return this;
    }

    @JsonProperty("meta_title")
    public String getMetaTitle() {
        return meta_title;
    }

    @JsonProperty("meta_title")
    public void setMetaTitle(String metaTitle) {
        this.meta_title = metaTitle;
    }

    public Taxon withMetaTitle(String metaTitle) {
        this.meta_title = metaTitle;
        return this;
    }

    @JsonProperty("meta_description")
    public String getMetaDescription() {
        return meta_description;
    }

    @JsonProperty("meta_description")
    public void setMetaDescription(String metaDescription) {
        this.meta_description = metaDescription;
    }

    public Taxon withMetaDescription(String metaDescription) {
        this.meta_description = metaDescription;
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

    public Taxon withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("banner_image")
    public Object getBannerImage() {
        return banner_image;
    }

    @JsonProperty("banner_image")
    public void setBannerImage(Object bannerImage) {
        this.banner_image = bannerImage;
    }

    public Taxon withBannerImage(Object bannerImage) {
        this.banner_image = bannerImage;
        return this;
    }

    @JsonProperty("taxons")
    public List<Taxon> getTaxons() {
        return taxons;
    }

    @JsonProperty("taxons")
    public void setTaxons(List<Taxon> taxons) {
        this.taxons = taxons;
    }

    public Taxon withTaxons(List<Taxon> taxons) {
        this.taxons = taxons;
        return this;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Taxon withIcon(String icon) {
        this.icon = icon;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
