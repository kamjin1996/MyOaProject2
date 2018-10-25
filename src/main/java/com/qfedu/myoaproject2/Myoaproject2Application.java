package com.qfedu.myoaproject2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.qfedu.myoaproject2.mapper")
@ServletComponentScan
public class Myoaproject2Application {

    public static void main(String[] args) {
        SpringApplication.run(Myoaproject2Application.class, args);
    }
}