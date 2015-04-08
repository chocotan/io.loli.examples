package io.loli.example.ibatis.controller;

import io.loli.example.ibatis.service.IClazzService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class IndexController {
    @Autowired
    private IClazzService clazzService;

    @RequestMapping(value = { "", "index" })
    public String index() {
        return "index";
    }

}
