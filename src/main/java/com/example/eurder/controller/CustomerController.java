package com.example.eurder.controller;

import com.example.eurder.domain.customer.UserDTO;
import com.example.eurder.mapper.UserMapper;
import com.example.eurder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path = "/customer")
public class CustomerController {

    private final UserService userService;
    public final Logger logger =  LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllCustomers() {

        return userService.findAllCustomers();
    }

    @GetMapping(path = "/{uuid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO FindById(@PathVariable String uuid) {
        return this.userService.findById(uuid);
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerMember(@RequestBody UserDTO userDTO){
        userService.registerCustomer(userDTO);
        logger.info(String.format("User with name %s is created",userDTO.getFirstName()));
    }
}
