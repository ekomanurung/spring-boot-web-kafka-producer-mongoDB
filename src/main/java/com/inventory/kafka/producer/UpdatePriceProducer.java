package com.inventory.kafka.producer;

/**
 * Created by eko.j.manurung on 6/6/2016.
 */
public interface UpdatePriceProducer {

    void updateDataPriceAndPublishToKafka(String id, double price);
}
