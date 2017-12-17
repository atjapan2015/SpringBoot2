package com.springboot2.business.sample.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot2.business.sample.entity.WorkInfoForm;

@Controller
public class ValidateController {

	@RequestMapping("/addworkinfo.html")
	public @ResponseBody void addWorkInfo(@Validated({ WorkInfoForm.Add.class }) WorkInfoForm workInfo,
			BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			FieldError error = (FieldError) list.get(0);
			System.out.println(error.getObjectName() + "," + error.getField() + "," + error.getDefaultMessage());
			return;
		}
		return;
	}
}
