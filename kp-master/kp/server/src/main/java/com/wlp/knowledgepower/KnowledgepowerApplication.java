package com.wlp.knowledgepower;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan(basePackages = "com.example.demo.dao")
@SpringBootApplication
@MapperScan(basePackages = "com.wlp.knowledgepower.dao")
public class KnowledgepowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgepowerApplication.class, args);
    }

}
