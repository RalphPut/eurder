package com.example.eurder.domain.customer;

import java.util.UUID;

public class CustomerDTO {
    private String uuid;
    private final String firstName;
    private final String lastName;
    private final Address address;
    private final String phoneNumber;
    private final UserRole userRole;



    public CustomerDTO(String uuid, String firstName, String lastName, Address address, String phoneNumber, UserRole userRole) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

  public void setUuid(String uuid){
        this.uuid = uuid;
    }
    public String getLastName() {
        return lastName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
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
