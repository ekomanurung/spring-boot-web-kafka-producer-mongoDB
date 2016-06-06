package com.inventory.dao;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
public interface InventoryRepositoryCustom {

    public int updateStockProduct(String id, int stock);

    public int updatePriceProduct(String id, double price);
}
