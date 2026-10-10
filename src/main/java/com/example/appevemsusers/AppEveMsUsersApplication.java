package com.example.appevemsusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppEveMsUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppEveMsUsersApplication.class, args);
    }

}
