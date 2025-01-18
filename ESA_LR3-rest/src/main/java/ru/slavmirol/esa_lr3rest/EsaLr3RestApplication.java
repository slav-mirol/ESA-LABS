package ru.slavmirol.esa_lr3rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EsaLr3RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsaLr3RestApplication.class, args);
    }

}
