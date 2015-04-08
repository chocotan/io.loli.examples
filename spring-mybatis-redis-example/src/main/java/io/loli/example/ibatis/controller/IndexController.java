package io.loli.example.ibatis.controller;

import io.loli.example.ibatis.service.IClazzService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "")
public class IndexController {
    @Autowired
    private RedisTemplate<String, String> template;
    
    @Autowired
    private IClazzService clazzService;

    @RequestMapping(value = { "", "index" })
    public String index() {
        template.opsForValue().set("test2", "test");
        return "index";
    }

    @RequestMapping(value = "test")
    @ResponseBody
    public String test() {
        clazzService.findTest();
        return "test";
    }

}
