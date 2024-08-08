package com.inventory.controller;

import com.inventory.dao.InventoryRepository;
import com.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eko.j.manurung on 9/13/2016.
 */
@RequestMapping(value = "/api/v1/inventories")
@RestController
public class InventoryRestController {

    @Autowired
    private InventoryRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable("id") String id) {
        repository
                .findById(id)
                .ifPresent(inventory -> new ResponseEntity<>(inventory, HttpStatus.OK));

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> findAll(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(repository.findAll(PageRequest.of(page, size)).getContent(), HttpStatus.OK);
    }
}
