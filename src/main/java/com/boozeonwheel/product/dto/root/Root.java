
package com.boozeonwheel.product.dto.root;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.boozeonwheel.product.dto.taxons.Taxon;
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
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pretty_name")
    private String pretty_name;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("parent_id")
    private Object parent_id;
    @JsonProperty("taxonomy_id")
    private Integer taxonomy_id;
    @JsonProperty("meta_title")
    private Object metaTitle;
    @JsonProperty("meta_description")
    private Object metaDescription;
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

    public Root withId(Integer id) {
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
    public void setParentId(Object parentId) {
        this.parent_id = parentId;
    }

    public Root withParentId(Object parentId) {
        this.parent_id = parentId;
        return this;
    }

    @JsonProperty("taxonomy_id")
    public Integer getTaxonomyId() {
        return taxonomy_id;
    }

    @JsonProperty("taxonomy_id")
    public void setTaxonomyId(Integer taxonomyId) {
        this.taxonomy_id = taxonomyId;
    }

    public Root withTaxonomyId(Integer taxonomyId) {
        this.taxonomy_id = taxonomyId;
        return this;
    }

    @JsonProperty("meta_title")
    public Object getMetaTitle() {
        return metaTitle;
    }

    @JsonProperty("meta_title")
    public void setMetaTitle(Object metaTitle) {
        this.metaTitle = metaTitle;
    }

    public Root withMetaTitle(Object metaTitle) {
        this.metaTitle = metaTitle;
        return this;
    }

    @JsonProperty("meta_description")
    public Object getMetaDescription() {
        return metaDescription;
    }

    @JsonProperty("meta_description")
    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Root withMetaDescription(Object metaDescription) {
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
