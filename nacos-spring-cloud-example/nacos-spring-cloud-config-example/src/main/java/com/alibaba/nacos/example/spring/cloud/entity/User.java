package com.alibaba.nacos.example.spring.cloud.entity;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "user")
@Data
public class User implements InitializingBean, DisposableBean {

    private String name;

    private int age;


    private String name2;

    private int age2;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", name2='" + name2 + '\'' +
                ", age2=" + age2 +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[destroy()] " + toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[afterPropertiesSet()] " + toString());
    }
}
