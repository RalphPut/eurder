package com.example.eurder.domain.customer;

import org.springframework.beans.factory.annotation.Required;

import java.util.UUID;

public class Customer {
    private String uuid;
    private final String firstName;
    private final String lastName;
    private final Address address;
    private final String phoneNumber;
    private final UserRole userRole;


    public Customer(String firstName, String lastName, Address address, String phoneNumber, UserRole userRole) {
        this.uuid=UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }


    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
