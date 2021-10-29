package com.example.eurder.controller;

import com.example.eurder.domain.item.ItemDTO;
import com.example.eurder.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;
    public final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDTO> getAllItemsOrdered(){
        return itemService.findAllItemsOrderedOnStockLevel();
    }

    @GetMapping(path = "/{itemId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDTO findById(@PathVariable String itemId){
        return this.itemService.findById(itemId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody ItemDTO itemDTO, @RequestHeader(value ="uuid", required = false)String userId){
        itemService.addItem(userId,itemDTO);
    }
}
