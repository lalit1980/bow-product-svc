package com.boozeonwheel.product.domain.master;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptionValue")
public class OptionValue {
	private Integer id;
	private String name;
	private String presentation;
	private String optionTypeName;
	private Integer optionTypeId;
	private String optionTypePresentation;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPresentation() {
		return presentation;
	}
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	public String getOptionTypeName() {
		return optionTypeName;
	}
	public void setOptionTypeName(String optionTypeName) {
		this.optionTypeName = optionTypeName;
	}
	public Integer getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Integer optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	public String getOptionTypePresentation() {
		return optionTypePresentation;
	}
	public void setOptionTypePresentation(String optionTypePresentation) {
		this.optionTypePresentation = optionTypePresentation;
	}
	@Override
	public String toString() {
		return "OptionValue [id=" + id + ", name=" + name + ", presentation=" + presentation + ", optionTypeName="
				+ optionTypeName + ", optionTypeId=" + optionTypeId + ", optionTypePresentation="
				+ optionTypePresentation + "]";
	}
}
