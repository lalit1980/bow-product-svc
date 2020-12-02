
package com.boozeonwheel.product.dto.master;

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
    "presentation",
    "option_type_name",
    "option_type_id",
    "option_type_presentation"
})
public class OptionValue {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("presentation")
    private String presentation;
    @JsonProperty("option_type_name")
    private String optionTypeName;
    @JsonProperty("option_type_id")
    private Integer optionTypeId;
    @JsonProperty("option_type_presentation")
    private String optionTypePresentation;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public OptionValue withId(Integer id) {
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

    public OptionValue withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("presentation")
    public String getPresentation() {
        return presentation;
    }

    @JsonProperty("presentation")
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public OptionValue withPresentation(String presentation) {
        this.presentation = presentation;
        return this;
    }

    @JsonProperty("option_type_name")
    public String getOptionTypeName() {
        return optionTypeName;
    }

    @JsonProperty("option_type_name")
    public void setOptionTypeName(String optionTypeName) {
        this.optionTypeName = optionTypeName;
    }

    public OptionValue withOptionTypeName(String optionTypeName) {
        this.optionTypeName = optionTypeName;
        return this;
    }

    @JsonProperty("option_type_id")
    public Integer getOptionTypeId() {
        return optionTypeId;
    }

    @JsonProperty("option_type_id")
    public void setOptionTypeId(Integer optionTypeId) {
        this.optionTypeId = optionTypeId;
    }

    public OptionValue withOptionTypeId(Integer optionTypeId) {
        this.optionTypeId = optionTypeId;
        return this;
    }

    @JsonProperty("option_type_presentation")
    public String getOptionTypePresentation() {
        return optionTypePresentation;
    }

    @JsonProperty("option_type_presentation")
    public void setOptionTypePresentation(String optionTypePresentation) {
        this.optionTypePresentation = optionTypePresentation;
    }

    public OptionValue withOptionTypePresentation(String optionTypePresentation) {
        this.optionTypePresentation = optionTypePresentation;
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
