package com.alibaba.nacos.example.spring.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @LoadBalanced
    @Bean("loadBalancedRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
