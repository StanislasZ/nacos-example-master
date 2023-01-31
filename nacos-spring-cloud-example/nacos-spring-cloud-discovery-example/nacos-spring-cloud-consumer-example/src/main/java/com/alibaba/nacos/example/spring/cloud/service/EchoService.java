package com.alibaba.nacos.example.spring.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * openFeign 服务声明接口
 */
@FeignClient("nacos-discovery-service-provider")
public interface EchoService {

    @RequestMapping(value = "/echo/{message}", method = RequestMethod.GET)
    String echo(@PathVariable("message") String message);
}
