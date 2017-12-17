package com.springboot2.business.sample.entity;

import com.fasterxml.jackson.annotation.JsonView;

public class Department {

	public interface IdView {
	};

	public interface IdNameView extends IdView {
	}

	@JsonView(IdView.class)
	private Long id;
	@JsonView(IdNameView.class)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
