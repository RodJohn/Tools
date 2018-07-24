package com.john.rod.booting;


import lombok.extern.log4j.Log4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Log4j
@SpringBootApplication(scanBasePackages="com.john.rod")
public class MainApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application is running .");
    }
}
