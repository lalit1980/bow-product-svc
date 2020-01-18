
package com.boozeonwheel.product.dto.banner;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.boozeonwheel.product.dto.taxonomies.Taxonomy;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
@JsonPropertyOrder({
    "taxonomies",
    "count",
    "current_page",
    "pages"
})
public class Banner {

    @JsonProperty("taxonomies")
    private List<Taxonomy> taxonomies = new ArrayList<Taxonomy>();
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("current_page")
    private Integer currentPage;
    @JsonProperty("pages")
    private Integer pages;

    @JsonProperty("taxonomies")
    public List<Taxonomy> getTaxonomies() {
        return taxonomies;
    }

    @JsonProperty("taxonomies")
    public void setTaxonomies(List<Taxonomy> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public Banner withTaxonomies(List<Taxonomy> taxonomies) {
        this.taxonomies = taxonomies;
        return this;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    public Banner withCount(Integer count) {
        this.count = count;
        return this;
    }

    @JsonProperty("current_page")
    public Integer getCurrentPage() {
        return currentPage;
    }

    @JsonProperty("current_page")
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Banner withCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    @JsonProperty("pages")
    public Integer getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Banner withPages(Integer pages) {
        this.pages = pages;
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
