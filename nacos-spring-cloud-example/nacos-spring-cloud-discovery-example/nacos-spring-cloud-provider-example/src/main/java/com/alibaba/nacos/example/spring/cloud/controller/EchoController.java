package com.alibaba.nacos.example.spring.cloud.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @RequestMapping(value = "/echo/{message}", method = RequestMethod.GET)
    public String echo(@PathVariable String message) {
        System.out.println("provider...  into echo...  param = " + message);
        return "Hello Nacos Discovery " + message;
    }


}
