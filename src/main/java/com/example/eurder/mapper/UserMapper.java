package com.example.eurder.mapper;

import com.example.eurder.domain.customer.Customer;
import com.example.eurder.domain.customer.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public CustomerDTO toDto(Customer user) {
        return new CustomerDTO(user.getUuid(), user.getFirstName(), user.getLastName(), user.getAddress(),
                user.getPhoneNumber(), user.getUserRole());
    }

    public List<CustomerDTO> toDto(List<Customer> userList) {
        return userList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Customer toUser(CustomerDTO userDTO) {
        return new Customer(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(), userDTO.getPhoneNumber(),
                userDTO.getUserRole());
    }
}
