package com.alibaba.nacos.example.spring.cloud.controller;

import com.alibaba.nacos.example.spring.cloud.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    EchoService echoService;


    @Autowired
    RestTemplate loadBalancedRestTemplate;


    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        System.out.println("consumer.... into echo...  str = " + str);
        //传统 spring-cloud方式调用
        return loadBalancedRestTemplate.getForObject("http://nacos-discovery-service-provider/echo/" + str, String.class);
    }

    @RequestMapping("/feign/echo/{message}")
    public String feignEcho(@PathVariable String message) {
        System.out.println("consumer... into feign echo...  message = " + message);
        //调用 openFeign服务声明接口
        return echoService.echo(message);
    }








}
