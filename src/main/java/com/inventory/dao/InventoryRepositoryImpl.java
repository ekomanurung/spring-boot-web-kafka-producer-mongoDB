package com.inventory.dao;

import com.inventory.model.Inventory;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
public class InventoryRepositoryImpl implements InventoryRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int updateStockProduct(String id, int stock) {

        Query query = new Query();
                query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update();
        update.set("stock", stock);

        WriteResult result = mongoTemplate.updateFirst(query, update, Inventory.class, Inventory.COLLECTION_NAME);

        return result.getN();
    }

    @Override
    public int updatePriceProduct(String id, double price) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update();
        update.set("price", price);

        WriteResult result = mongoTemplate.updateFirst(query, update, Inventory.class, Inventory.COLLECTION_NAME);

        return result.getN();
    }
}
