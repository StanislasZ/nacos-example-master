package com.alibaba.nacos.example.spring.cloud;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.example.spring.cloud.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * Document: https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html
 */
@SpringBootApplication
@RefreshScope
@RestController
@EnableConfigurationProperties(User.class)
public class NacosConfigApplication {


    @Value("${user.name}")
    private String userName;
    @Value("${user.age}")
    private int userAge;


    @Resource
    User user;

    @Resource
    private NacosConfigManager nacosConfigManager;

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            String dataId = "example.properties";
            String group = "DEFAULT_GROUP";
            nacosConfigManager.getConfigService().addListener(dataId, group, new
                    AbstractListener() {
                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            System.out.println("[Listener] " + configInfo);

                            System.out.println("[Before User] " + user);

                            Properties properties = new Properties();
                            try {
                                properties.load(new StringReader(configInfo));
                                String name = properties.getProperty("user.name");
                                int age = Integer.valueOf(properties.getProperty("user.age"));
                                user.setName(name);
                                user.setAge(age);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("[After User] " + user);



                        }
                    });
        };
    }

    @PostConstruct
    public void init() {
        System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
    }

    @PreDestroy
    public void destroy() {
        System.out.printf("[destroy] user name : %s , age : %d%n", userName, userAge);
    }

    @RequestMapping("/user")
    public String user() throws JsonProcessingException {
        System.out.println("[HTTP]" + user);
        return "[HTTP] " + user;
    }


    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }
}


