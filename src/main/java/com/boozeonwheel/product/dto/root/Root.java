
package com.boozeonwheel.product.dto.root;

import java.util.ArrayList;
import java.util.List;

import com.boozeonwheel.product.dto.taxons.Taxon;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
    "taxons"
})
public class Root {

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
    private String metaTitle;
    @JsonProperty("meta_description")
    private String metaDescription;
    @JsonProperty("taxons")
    private List<Taxon> taxons = new ArrayList<Taxon>();

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public Root withId(long id) {
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

    public Root withName(String name) {
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

    public Root withPrettyName(String prettyName) {
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

    public Root withPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    @JsonProperty("parent_id")
    public Object getParentId() {
        return parent_id;
    }

    @JsonProperty("parent_id")
    public void setParentId(long parentId) {
        this.parent_id = parentId;
    }

    public Root withParentId(long parentId) {
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

    public Root withTaxonomyId(long taxonomyId) {
        this.taxonomy_id = taxonomyId;
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

    public Root withMetaTitle(String metaTitle) {
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

    public Root withMetaDescription(String metaDescription) {
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

    public Root withTaxons(List<Taxon> taxons) {
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
