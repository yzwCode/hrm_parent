package com.yzw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yzw.mapper")
public class ManagementService9001Application {
    public static void main(String[] args) {
        SpringApplication.run(ManagementService9001Application.class,args);
    }
}
