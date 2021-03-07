package com.example.fs.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class Test {
    @GetMapping
    public Map<String, Object> testGet() {
        return Map.of("key", "foo", "value", "hoge");
    }
}
