package com.inventory.controller;

import com.inventory.dao.InventoryRepository;
import com.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eko.j.manurung on 9/13/2016.
 */
@RequestMapping(value = "/api/v1/inventory")
@RestController
public class InventoryRestController {

    @Autowired
    private InventoryRepository repository;

    @GetMapping
    public ResponseEntity<Inventory> getInventory(@RequestParam(value = "id", defaultValue = "1") String id) {
        repository
                .findById(id)
                .ifPresent(inventory -> new ResponseEntity<>(inventory, HttpStatus.OK));

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
