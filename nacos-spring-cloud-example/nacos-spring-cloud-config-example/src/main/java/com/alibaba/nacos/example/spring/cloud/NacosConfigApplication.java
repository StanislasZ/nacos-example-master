package com.alibaba.nacos.example.spring.cloud;

import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.example.spring.cloud.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Document: https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html
 */
@SpringBootApplication
@RefreshScope
@RestController
public class NacosConfigApplication {



    @Value("${user.name}")
    private String userName;
    @Value("${user.age}")
    private int userAge;


    @Resource
    User user;

//    @Resource
//    private NacosConfigManager nacosConfigManager;
//
//    @Bean
//    public ApplicationRunner runner() {
//        return args -> {
//            String dataId = "example.properties";
//            String group = "DEFAULT_GROUP";
//            nacosConfigManager.getConfigService().addListener(dataId, group, new
//                    AbstractListener() {
//                        @Override
//                        public void receiveConfigInfo(String configInfo) {
//                            System.out.println("[Listener] " + configInfo);
//                        }
//                    });
//        };
//    }

    @PostConstruct
    public void init() {
        System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
    }

    @PreDestroy
    public void destroy() {
        System.out.printf("[destroy] user name : %s , age : %d%n", userName, userAge);
    }

    @RequestMapping("/user")
    public String user() {
        return "[HTTP] " + user;
    }


    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }
}


