package com.springboot2.business.sample.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot2.business.sample.dataset.TUser;
import com.springboot2.business.sample.entity.Department;
import com.springboot2.business.sample.entity.User;
import com.springboot2.business.sample.facade.SampleFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Sample Api")
@Controller
public class SampleController {

    @Autowired
    SampleFacade sampleFacade;

    @Autowired
    ObjectMapper mapper;

    @ApiOperation("Get Current Datetime")
    @ApiImplicitParams({})
    @ApiResponses({ @ApiResponse(code = 200, message = "OK") })
    @GetMapping("now.json")
    public @ResponseBody Map<String, Date> datetime() {
        Map<String, Date> map = new HashMap<>();
        map.put("time", new Date());
        return map;
    }

    @GetMapping("readtree.json")
    public @ResponseBody String readtree() throws JsonProcessingException, IOException {
        String json = "{\"name\":\"lijz\",\"id\":10}";
        JsonNode node = mapper.readTree(json);
        String name = node.get("name").asText();
        int id = node.get("id").asInt();
        return "name:" + name + ",id:" + id;
    }

    @GetMapping("/databind.json")
    public @ResponseBody String databind() throws JsonProcessingException, IOException {
        String json = "{\"name\":\"lijz\",\"id\":10}";
        User user = mapper.readValue(json, User.class);
        return "name:" + user.getName() + ",id:" + user.getId();
    }

    @GetMapping("/serialization.json")
    public @ResponseBody String custom() throws JsonProcessingException, IOException {
        User user = new User();
        user.setId((long) 11);
        user.setName("Hello");
        String str = mapper.writeValueAsString(user);
        return str;
    }

    @RequestMapping("/parser.html")
    public @ResponseBody String parser() throws JsonParseException, IOException {
        String json = "{\"name\":\"lijz\",\"id\":10}";
        JsonFactory factory = mapper.getFactory();
        String key = null, value = null;
        JsonParser parser = factory.createParser(json);
        // {, START_OBJECT, 忽略第一个Token
        JsonToken token = parser.nextToken();
        // "name",FIELD_NAME
        token = parser.nextToken();
        if (token == JsonToken.FIELD_NAME) {
            key = parser.getCurrentName();
        }
        token = parser.nextToken();
        // "lijz", VALUE_STRING
        value = parser.getValueAsString();
        parser.close();
        return key + "," + value;
    }

    @RequestMapping("/generator.html")
    public @ResponseBody String generator() throws JsonParseException, IOException {
        JsonFactory factory = mapper.getFactory();
        StringWriter sw = new StringWriter();
        JsonGenerator g = factory.createGenerator(sw);
        // {
        g.writeStartObject();
        g.writeStringField("name", "lijiazhi");
        // }
        g.writeEndObject();
        g.close();
        return sw.toString();
    }

    @RequestMapping("updateUsers.json")
    public @ResponseBody String say(@RequestBody List<User> list) {
        StringBuilder sb = new StringBuilder();
        // for (User user : list) {
        // sb.append(user.getName()).append(" ");
        // }
        list.parallelStream().forEach(user -> {
            sb.append(user.getName()).append(" ");
        });
        return sb.toString();
    }

    @JsonView(Department.IdView.class)
    @RequestMapping("/id.json")
    public @ResponseBody Department queryIds() {
        Department d = new Department();
        d.setId((long) 1);
        d.setName("hello");
        return d;
    }

    @RequestMapping("/user/{id}")
    public @ResponseBody TUser getUserById(@PathVariable Short id) {

        TUser user = sampleFacade.selectByPrimaryKey(id);
        return user;

    }
}
