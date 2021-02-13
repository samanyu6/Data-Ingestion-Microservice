package com.microservices.demo.twitter.to.kafka.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterToKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaApplication.class, args);
    }
}