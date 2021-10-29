package com.example.eurder.mapper;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.item.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemDTO toDto(Item item){
        return new ItemDTO(item.getId(),item.getName(), item.getDescription(), item.getPrice(), item.getAmount());
    }

    public List<ItemDTO> toDto(List<Item> itemList){
        return itemList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public  Item toItem(ItemDTO itemDTO){
        return new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getAmount());
    }
}
