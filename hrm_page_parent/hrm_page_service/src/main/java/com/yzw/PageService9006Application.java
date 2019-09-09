package com.yzw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yzw.mapper")
@EnableFeignClients
public class PageService9006Application {
    public static void main(String[] args) {
        SpringApplication.run(PageService9006Application.class,args);
    }
}
