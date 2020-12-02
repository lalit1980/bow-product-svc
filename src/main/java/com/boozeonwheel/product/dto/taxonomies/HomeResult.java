package com.boozeonwheel.product.dto.taxonomies;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
@JsonPropertyOrder({ "taxonomies" })
public class HomeResult {
	@JsonProperty("taxonomies")
	private List<Taxonomy> taxonomies = new ArrayList<Taxonomy>();

	@JsonProperty("taxonomies")
	public List<Taxonomy> getTaxonomies() {
		return taxonomies;
	}

	@JsonProperty("taxonomies")
	public void setTaxonomies(List<Taxonomy> taxonomies) {
		this.taxonomies = taxonomies;
	}

	public HomeResult withTaxons(List<Taxonomy> taxonomies) {
		this.taxonomies = taxonomies;
		return this;
	}
}
