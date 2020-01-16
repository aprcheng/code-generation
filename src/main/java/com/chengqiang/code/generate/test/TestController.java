package com.chengqiang.code.generate.test;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    public String hello() {
        return "hello";
    }
}
