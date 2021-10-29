package com.example.eurder.service;

import com.example.eurder.domain.customer.UserRole;
import com.example.eurder.domain.item.ItemDTO;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.repository.ItemRepository;
import com.example.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;

    @Autowired

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, UserRepository
            userRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userRepository=userRepository;
    }

    public ItemDTO findById(String itemId) {
        try {
            return itemMapper.toDto(itemRepository.findByid(itemId));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<ItemDTO> findAllItemsOrderedOnStockLevel() {
        return itemMapper.toDto(itemRepository.findAllItems());
    }

    public void addItem(String uuid, ItemDTO itemDTO) {

        try{
            if (userIsAdmin(uuid)){
              itemRepository.addItem(itemMapper.toItem(itemDTO));
            }
            else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
        catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Item was not added!");
        }
    }


    public boolean userIsAdmin(String userId) {

        try {
            return userRepository.findByid(userId).getUserRole() == UserRole.ADMIN;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
