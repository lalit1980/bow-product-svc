
package com.boozeonwheel.product.dto.taxonomies;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.boozeonwheel.product.dto.banner.Banner;
import com.boozeonwheel.product.dto.root.Root;
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
    "root",
    "count",
    "current_page",
    "pages"
})
public class Taxonomy {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("root")
    private Root root;
    
    @JsonProperty("count")
    private int count;
    
    @JsonProperty("current_page")
    private int current_page;
    
    @JsonProperty("pages")
    private int pages;
    
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Taxonomy withId(Integer id) {
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

    public Taxonomy withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("root")
    public Root getRoot() {
        return root;
    }

    @JsonProperty("root")
    public void setRoot(Root root) {
        this.root = root;
    }

    public Taxonomy withRoot(Root root) {
        this.root = root;
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
    @JsonProperty("count")
	public int getCount() {
		return count;
	}
    
    @JsonProperty("count")
	public void setCount(int count) {
		this.count = count;
	}
    
    public Taxonomy withCount(Integer count) {
        this.count = count;
        return this;
    }

    @JsonProperty("current_page")
	public int getCurrent_page() {
		return current_page;
	}

    @JsonProperty("current_page")
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
    
    public Taxonomy withCurrentPages(Integer current_page) {
        this.current_page = current_page;
        return this;
    }

    @JsonProperty("pages")
	public int getPages() {
		return pages;
	}
    
    @JsonProperty("pages")
	public void setPages(int pages) {
		this.pages = pages;
	}
    public Taxonomy withPages(Integer pages) {
        this.pages = pages;
        return this;
    }
    

}
