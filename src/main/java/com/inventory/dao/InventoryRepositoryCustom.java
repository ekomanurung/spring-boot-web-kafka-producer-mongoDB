package com.inventory.dao;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
public interface InventoryRepositoryCustom {

    long updateStockProduct(String id, int stock);

    long updatePriceProduct(String id, double price);
}
