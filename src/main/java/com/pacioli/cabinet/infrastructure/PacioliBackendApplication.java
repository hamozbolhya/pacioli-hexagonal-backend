package com.pacioli.cabinet.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pacioli.cabinet")
public class PacioliBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacioliBackendApplication.class, args);
    }

}
