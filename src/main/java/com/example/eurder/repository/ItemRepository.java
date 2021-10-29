package com.example.eurder.repository;

import com.example.eurder.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

    private final Map<String, Item> itemDatabase;
    public final Logger logger = LoggerFactory.getLogger(ItemRepository.class);

    public ItemRepository() {
        this.itemDatabase = new ConcurrentHashMap<>();
        Item boek = new Item("Hallo België","Een dummybook.",100,1);
        itemDatabase.put(boek.getId(),boek);
        Item zakdoek = new Item("Zakdoek","Om neus te snuiten.",100,6);
        itemDatabase.put(zakdoek.getId(),zakdoek);
        Item Koffietas = new Item("Koffietas","Om uit te drinken.",100,10);
        itemDatabase.put(Koffietas.getId(),Koffietas);
        Item televisie = new Item("150 inch TV","Extra grote tv voor rijken mensen",100,100);
        itemDatabase.put(televisie.getId(),televisie);

    }

    public Item findByid(String itemID){
        var foundItem = this.itemDatabase.get(itemID);
        if (foundItem==null){
            logger.warn("This user does not exist!");
            throw new IllegalArgumentException(String.format("User with id %s does not exist!",itemID));
        }
        return foundItem;
    }

    public List<Item> findAllItems() {
        return itemDatabase.values().stream()
                .sorted(Item::compareTo)
                .collect(Collectors.toList());
    }

    public void addItem (Item item){
        logger.warn("Item id is "+item.getId());
        if (existsInDatabase(item.getId())){
            logger.error("Object already exists in database!");
            throw new IllegalArgumentException("Object already exists!");

        }
        else{
            itemDatabase.put(item.getId(),item);
        }
    }

    public boolean existsInDatabase(String uuid){
        return itemDatabase.containsKey(uuid);
    }


}
