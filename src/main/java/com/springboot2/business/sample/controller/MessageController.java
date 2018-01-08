package com.springboot2.business.sample.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    MessageSource message;

    @RequestMapping("/messages")
    public String showMessages() {
        String value = message.getMessage("E1", null, Locale.ROOT);
        return value;
    }

}
