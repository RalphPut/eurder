package com.example.eurder.service;

import com.example.eurder.domain.customer.CustomerDTO;
import com.example.eurder.domain.customer.UserRole;
import com.example.eurder.mapper.UserMapper;
import com.example.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public CustomerDTO findById(String uuid, String userID) {

        try {
            if (userIsAdmin(userID)) {
                return userMapper.toDto(userRepository.findByid(uuid));
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public List<CustomerDTO> findAllCustomers() {
        return userMapper.toDto(userRepository.findAllCustomers());
    }

    public void registerCustomer(CustomerDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("Unable to register.");
        }
        userRepository.registerCustomer(userMapper.toUser(userDTO));
    }

    public boolean userIsAdmin(String userId) {

        try {
            return userRepository.findByid(userId).getUserRole() == UserRole.ADMIN;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
