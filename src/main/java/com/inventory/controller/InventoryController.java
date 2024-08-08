package com.inventory.controller;

import com.inventory.dao.InventoryRepository;
import com.inventory.external.producer.UpdatePriceProducer;
import com.inventory.model.Inventory;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
@Controller
public class InventoryController {


    private InventoryRepository repository;
    private UpdatePriceProducer updatePriceProducer;

    public InventoryController(InventoryRepository repository, UpdatePriceProducer updatePriceProducer) {
        this.repository = repository;
        this.updatePriceProducer = updatePriceProducer;
    }

    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public ModelAndView updateStockInventory(@ModelAttribute("id") String id, @ModelAttribute("stock") int stock) {
        long retval = repository.updateStockProduct(id, stock);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "editStock/{id}", method = RequestMethod.GET)
    public String updateStockInventory(Model model, @PathVariable String id) {
        repository
                .findById(id)
                .ifPresent(model::addAttribute);

        return "updateStock";
    }

    @RequestMapping(value = "editPrice/{id}", method = RequestMethod.GET)
    public String updatePriceInventory(Model model, @PathVariable String id) {
        repository
                .findById(id)
                .ifPresent(model::addAttribute);

        return "updatePrice";
    }

    @RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
    public ModelAndView updatePriceInventory(@ModelAttribute("id") String id, @ModelAttribute("price") double newPrice) {
        long result = repository.updatePriceProduct(id, newPrice);

        if (result > 0) {
            updatePriceProducer.publishPriceChange(id, newPrice);
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addInventory(@ModelAttribute("inventory") Inventory inventory, Model model) {
        repository.save(inventory);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView addInventory(HttpSession httpSession) {
        return new ModelAndView("create");
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable String id) {
        repository.deleteById(id);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/")
    public ModelAndView getAll(HttpSession httpSession) {
        List<Inventory> result = repository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("inventories", result);

        return modelAndView;
    }
}
