package com.llm.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.llm.demo.mapper")
public class ImplApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImplApplication.class,args);
    }
}
