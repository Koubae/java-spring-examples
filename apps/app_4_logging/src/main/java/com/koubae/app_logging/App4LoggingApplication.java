package com.koubae.app_logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App4LoggingApplication {
    private static final Logger logger = LoggerFactory.getLogger(App4LoggingApplication.class);

    public static void main(String[] args) {
        logger.info("Started App");
        logger.warn("Started App");
        logger.error("Stated App");
        SpringApplication.run(App4LoggingApplication.class, args);
    }

}
