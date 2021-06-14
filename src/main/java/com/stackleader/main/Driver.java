package com.stackleader.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.stackleader")
public class Driver extends SpringBootServletInitializer implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {
        SpringApplication.run(Driver.class, args);
        LOG.info("Driver: main method called");
    }

    @Override
    public void run(String... args) throws Exception {}

}
