package com.inventory.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by eko.j.manurung on 6/6/2016.
 */
@Configuration
public class KafkaConfiguration {

    public static final String PRICE_CHANGE_TOPIC_NAME = "com.inventory.update.price";

    @Bean
    public NewTopic updateInventoryPrice() {
        return new NewTopic(PRICE_CHANGE_TOPIC_NAME, 1, (short) 1);
    }
}
