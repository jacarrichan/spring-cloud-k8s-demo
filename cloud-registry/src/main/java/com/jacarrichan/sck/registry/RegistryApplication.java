package com.jacarrichan.sck.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka server.
 *
 * @author jacarrichan 2017/05/27
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

    public static void main(String[] args) {

        SpringApplication.run(RegistryApplication.class, args);
    }
}
