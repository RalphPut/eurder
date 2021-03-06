package com.example.eurder.domain.customer;

public class Address {

    private final String streetName;
    private final int houseNumber;
    private final String postalCode;
    private final String city;

    public Address(String streetName, int houseNumber, String postalCode, String city) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }
}
