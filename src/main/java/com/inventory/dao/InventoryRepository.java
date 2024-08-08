package com.inventory.dao;

import com.inventory.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
public interface InventoryRepository extends MongoRepository<Inventory, String>, InventoryRepositoryCustom {
}
