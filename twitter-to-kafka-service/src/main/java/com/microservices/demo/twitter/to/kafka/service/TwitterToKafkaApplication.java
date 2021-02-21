package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.runner.TwitterKafkaRunnerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.demo")
public class TwitterToKafkaApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaApplication.class);
    private final TwitterKafkaRunnerInterface twitterKafkaRunnerInterface;

    public TwitterToKafkaApplication(TwitterKafkaRunnerInterface twitterKafkaRunnerInterface) {
        this.twitterKafkaRunnerInterface = twitterKafkaRunnerInterface;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Staring T2K Microservice");
        twitterKafkaRunnerInterface.start();
    }
}