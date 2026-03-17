package com.example.paciolibackend.Cabinet.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.paciolibackend.Cabinet")
public class PacioliBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacioliBackendApplication.class, args);
    }

}
