
package com.boozeonwheel.product.dto.master;

import javax.annotation.Generated;

import com.boozeonwheel.product.dto.taxons.Taxon;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "taxon_id",
    "position",
    "taxon"
})
public class Classification {

    @JsonProperty("taxon_id")
    private Integer taxonId;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("taxon")
    private com.boozeonwheel.product.dto.taxons.Taxon taxon;

    @JsonProperty("taxon_id")
    public Integer getTaxonId() {
        return taxonId;
    }

    @JsonProperty("taxon_id")
    public void setTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
    }

    public Classification withTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
        return this;
    }

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    public Classification withPosition(Integer position) {
        this.position = position;
        return this;
    }

    @JsonProperty("taxon")
    public com.boozeonwheel.product.dto.taxons.Taxon getTaxon() {
        return taxon;
    }

    @JsonProperty("taxon")
    public void setTaxon(com.boozeonwheel.product.dto.taxons.Taxon taxon) {
        this.taxon = taxon;
    }

    public Classification withTaxon(Taxon taxon) {
        this.taxon = taxon;
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
