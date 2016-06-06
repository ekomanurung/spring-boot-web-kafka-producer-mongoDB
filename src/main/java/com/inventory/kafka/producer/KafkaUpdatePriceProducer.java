package com.inventory.kafka.producer;

import com.inventory.dao.InventoryRepository;
import com.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * Created by eko.j.manurung on 6/6/2016.
 */
@Component
public class KafkaUpdatePriceProducer implements UpdatePriceProducer {

    @Autowired
    private InventoryRepository repository;

    private MessageChannel toKafka;

    @Autowired
    public KafkaUpdatePriceProducer(@Qualifier("toKafka") MessageChannel toKafka){
        this.toKafka = toKafka;
    }

    @Override
    public void updateDataPriceAndPublishToKafka(String id, double price) {

        Inventory inventory = repository.findById(id);
        //check if new price < old price (diskon)
        //send to kafka
        if(price < inventory.getPrice()){
            Message<?> content = new GenericMessage<String>(Double.toString(price));
            toKafka.send(content);
        }
        repository.updatePriceProduct(id, price);
    }
}
