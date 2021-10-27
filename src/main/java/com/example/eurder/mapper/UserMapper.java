package com.example.eurder.mapper;

import com.example.eurder.domain.customer.User;
import com.example.eurder.domain.customer.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getAddress(),
                user.getPhoneNumber(), user.getUserRole());
        userDTO.setUuid(user.getUuid());
        return userDTO;
    }

    public List<UserDTO> toDto(List<User> userList) {
        return userList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public User toUser(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(), userDTO.getPhoneNumber(),
                userDTO.getUserRole());
        user.setUuid(user.getUuid());
        return user;
    }
}
