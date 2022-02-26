package cn.wn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.swing.*;

@SpringBootApplication
@MapperScan("cn.wn.mapper")
public class ElasticApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class,args);
    }
}



