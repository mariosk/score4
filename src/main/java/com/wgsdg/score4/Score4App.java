package com.wgsdg.score4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wgsdg.score4.controller")
public class Score4App extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(Score4App.class);

    public static void main(String[] args) {
        SpringApplication.run(Score4App.class, args);
        logger.info("Test");
    }

}
