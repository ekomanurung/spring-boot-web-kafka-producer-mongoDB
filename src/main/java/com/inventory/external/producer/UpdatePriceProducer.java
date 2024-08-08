package com.inventory.external.producer;

import com.inventory.configuration.KafkaConfiguration;
import com.inventory.dao.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by eko.j.manurung on 6/6/2016.
 */
@Component
public class UpdatePriceProducer {

    private static final Logger log = LoggerFactory.getLogger(UpdatePriceProducer.class);
    private InventoryRepository repository;
    private KafkaTemplate<String, String> kafkaTemplate;

    public UpdatePriceProducer(InventoryRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPriceChange(String id, double price) {
        repository
                .findById(id)
                .ifPresent(inventory -> {
                    if (price < inventory.getPrice()) {
                        try {
                            kafkaTemplate.send(KafkaConfiguration.PRICE_CHANGE_TOPIC_NAME, id, Double.toString(price))
                                    .get();
                        } catch (Exception e) {
                            log.error("Error while sending message to Kafka topic {} with error ", KafkaConfiguration.PRICE_CHANGE_TOPIC_NAME, e);
                        }
                    }
                });
    }
}
