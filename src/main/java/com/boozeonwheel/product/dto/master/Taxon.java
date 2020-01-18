
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
    "pretty_name",
    "permalink",
    "parent_id",
    "taxonomy_id",
    "meta_title",
    "meta_description",
    "taxons"
})
public class Taxon {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pretty_name")
    private String prettyName;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("parent_id")
    private Integer parentId;
    @JsonProperty("taxonomy_id")
    private Integer taxonomyId;
    @JsonProperty("meta_title")
    private String metaTitle;
    @JsonProperty("meta_description")
    private String metaDescription;
    @JsonProperty("taxons")
    private List<Taxon> taxons = new ArrayList<Taxon>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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
        return prettyName;
    }

    @JsonProperty("pretty_name")
    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public Taxon withPrettyName(String prettyName) {
        this.prettyName = prettyName;
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
    public Integer getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Taxon withParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    @JsonProperty("taxonomy_id")
    public Integer getTaxonomyId() {
        return taxonomyId;
    }

    @JsonProperty("taxonomy_id")
    public void setTaxonomyId(Integer taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public Taxon withTaxonomyId(Integer taxonomyId) {
        this.taxonomyId = taxonomyId;
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

    public Taxon withMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
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

    public Taxon withMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
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
