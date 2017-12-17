package com.springboot2.business.sample.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class WorkInfoForm {

	public interface Update {
	}

	public interface Add {
	}

	@NotNull(groups = { Update.class })
	@Null(groups = { Add.class })
	Long id;
	@Size(min = 3, max = 20)
	String name;
	@Email
	String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
