package com.example.eurder.service;

import com.example.eurder.domain.customer.User;
import com.example.eurder.domain.customer.UserDTO;
import com.example.eurder.mapper.UserMapper;
import com.example.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserDTO findById(String uuid) {

        return userMapper.toDto(userRepository.findByid(uuid));
    }

    public List<UserDTO> findAllCustomers() {
        return userMapper.toDto(userRepository.findAllCustomers());
    }

    public void registerCustomer(UserDTO userDTO){
        if (userDTO==null){
            throw new IllegalArgumentException("Unable to register.");
        }
        userRepository.registerCustomer(userMapper.toUser(userDTO));
    }
}
