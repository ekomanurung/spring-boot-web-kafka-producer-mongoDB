package com.inventory.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
public class Inventory {

    public static final String COLLECTION_NAME = "inventory";

    @Id
    private String id;

    private String productName;
    private int stock;
    private double price;

    public Inventory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
