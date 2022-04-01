package com.zy.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaintenanceApplication {

    public static void main(String[] args) {
        System.out.println("V1.2");
        SpringApplication.run(MaintenanceApplication.class, args);
    }

}
