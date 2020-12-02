package com.boozeonwheel.product.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "label",
    "value"
})
@Getter
@Setter
@ToString
public class CategoryDTO {
	@JsonProperty("label")
	private String label;
	
	@JsonProperty("value")
	private String value;


}
