package com.collapseunion.commonapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author CloudSen
 */
@MapperScan("com.collapseunion.commonapi.cloudable.blog.mapper")
@SpringBootApplication
public class CommonApiApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CommonApiApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
