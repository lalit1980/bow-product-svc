
package com.boozeonwheel.product.dto.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pretty_name")
    private String prettyName;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("parent_id")
    private Long parentId;
    @JsonProperty("taxonomy_id")
    private Long taxonomyId;
    @JsonProperty("meta_title")
    private String metaTitle;
    @JsonProperty("meta_description")
    private String metaDescription;
    @JsonProperty("taxons")
    private List<Taxon> taxons = new ArrayList<Taxon>();


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
