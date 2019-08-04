package com.netease.network;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.netease.mapper")
@SpringBootApplication
public class NetworkApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(NetworkApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(NetworkApplication.class);
    }

}
