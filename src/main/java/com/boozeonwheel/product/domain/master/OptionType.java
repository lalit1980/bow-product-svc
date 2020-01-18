package com.boozeonwheel.product.domain.master;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptionType")
public class OptionType {
    private Integer id;
    private String name;
    private String presentation;
    private Integer position;
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
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "OptionType [id=" + id + ", name=" + name + ", presentation=" + presentation + ", position=" + position
				+ "]";
	}
}
