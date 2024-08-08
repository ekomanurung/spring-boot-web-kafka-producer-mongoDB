package com.inventory;

import com.inventory.configuration.KafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class InventoryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryAppApplication.class, args);
    }
}
