package com.cloudable.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CloudSen
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
public class BlogApplication {
    public static final String APPLICATION_NAME = "cloudable-blog";

    static {
        log.info("======> [ INFO ] Starting cloudable-blog server...");
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlogApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
