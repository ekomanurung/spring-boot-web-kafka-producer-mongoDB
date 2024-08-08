package com.inventory.controller;

import com.inventory.dao.InventoryRepository;
import com.inventory.external.producer.UpdatePriceProducer;
import com.inventory.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
@Controller
public class InventoryController {

    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);
    private InventoryRepository repository;
    private UpdatePriceProducer updatePriceProducer;

    public InventoryController(InventoryRepository repository, UpdatePriceProducer updatePriceProducer) {
        this.repository = repository;
        this.updatePriceProducer = updatePriceProducer;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Inventory> result = repository.findAll();
        model.addAttribute("inventories", result);

        return "index";
    }

    @GetMapping("/create")
    public String addInventory(Model model) {
        return "create_inventory";
    }

    @GetMapping("stocks/edit/{id}")
    public String updateStockInventory(Model model, @PathVariable String id) {
        repository
                .findById(id)
                .ifPresent(model::addAttribute);

        return "updateStock";
    }

    @GetMapping("prices/edit/{id}")
    public String updatePriceInventory(Model model, @PathVariable String id) {
        repository
                .findById(id)
                .ifPresent(model::addAttribute);

        return "updatePrice";
    }

    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public ModelAndView updateStockInventory(@ModelAttribute("id") String id, @ModelAttribute("stock") int stock) {
        long retval = repository.updateStockProduct(id, stock);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
    public ModelAndView updatePriceInventory(@ModelAttribute("id") String id, @ModelAttribute("price") double newPrice) {
        repository
                .findById(id)
                .ifPresent(inventory -> {
                    if (newPrice < inventory.getPrice()) {
                        updatePriceProducer.publishPriceChange(id, newPrice);
                    }
                    long modifiedRows = repository.updatePriceProduct(id, newPrice);
                    log.info("{} rows updated when update price", modifiedRows);
                });


        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addInventory(@ModelAttribute("inventory") Inventory inventory, Model model) {
        repository.save(inventory);
        log.info("Inventory {} successfully added", inventory);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable String id) {
        repository.deleteById(id);

        return new ModelAndView("redirect:/");
    }
}
