package com.llm.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SecuritiesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecuritiesApplication.class,args);
    }
}
