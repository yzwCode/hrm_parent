package com.yzw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ESApplication9004 {
    public static void main(String[] args) {
        SpringApplication.run(ESApplication9004.class,args);
    }
}
