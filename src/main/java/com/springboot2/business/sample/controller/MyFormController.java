package com.springboot2.business.sample.controller;

import java.util.Date;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFormController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
	}

	@RequestMapping("/date")
	public @ResponseBody void printDate(Date d) {
		System.out.println(d);
		return;
	}
}
