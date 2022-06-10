package com.example.herewegoagain;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class HereWeGoAgainApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HereWeGoAgainApplication.class).bannerMode(Banner.Mode.OFF).run(args);
    }

}
