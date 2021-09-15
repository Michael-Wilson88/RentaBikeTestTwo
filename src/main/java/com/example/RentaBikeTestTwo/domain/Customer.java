package com.example.RentaBikeTestTwo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private int age;
    private long phoneNumber;
    private String emailAddress;
    private String passportNumber;
    private String country;
    private String address;

    @OneToOne
    private Rental rental;


    public Customer(String firstName, String lastName, int age, long phoneNumber, String emailAddress,
                    String passportNumber, String country, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.passportNumber = passportNumber;
        this.country = country;
        this.address = address;
    }

    public Customer() {

    }
}