package com.inventory.external.producer;

import com.inventory.configuration.KafkaConfiguration;
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

    private KafkaTemplate<String, String> kafkaTemplate;

    public UpdatePriceProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPriceChange(String id, double price) {
        try {
            kafkaTemplate.send(KafkaConfiguration.PRICE_CHANGE_TOPIC_NAME, id, Double.toString(price)).get();

            log.info("Price change successfully published for product {} with new price {}", id, price);
        } catch (Exception e) {
            log.error("Error publishing price change for product {} with new price {}", id, price, e);
        }
    }
}
