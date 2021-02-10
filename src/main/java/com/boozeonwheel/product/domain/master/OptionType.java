package com.boozeonwheel.product.domain.master;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptionType")
public class OptionType {
	@Transient
    public static final String SEQUENCE_NAME = "optiontype_sequence";
	@Id
	private long id;
    private String name;
    private String presentation;
    private Integer position;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
