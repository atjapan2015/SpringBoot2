package com.springboot2.business.sample.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot2.business.sample.entity.WorkInfoForm;
import com.springboot2.common.validator.CommonValidator;

@Controller
public class ValidateController {

    @Autowired
    MessageSource message;

    @Autowired
    CommonValidator<WorkInfoForm> validator;

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

    @RequestMapping("customvalidate")
    public @ResponseBody String customizedValidate(WorkInfoForm tempform, BindingResult result) {

        String msg = "";

        WorkInfoForm form = new WorkInfoForm();
        form.setId((long) 1);
        form.setName("t");
        form.setEmail("emailemail.com");

        validator.validate(form, result, WorkInfoForm.Add.class);

        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            // FieldError error = (FieldError) list.get(0);
            // System.out.println(error.getObjectName() + "," + error.getField() + "," + error.getDefaultMessage());
            msg = message.getMessage("E1", null, Locale.ROOT);
        }

        return msg;
    }
}
