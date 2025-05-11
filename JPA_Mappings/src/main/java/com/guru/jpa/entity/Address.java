package com.guru.jpa.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address
{

    private String plotNo;

    private String streetName;

    private String subLocality;

    private String locality;

    private String city;

    private String State;

    private String pinCode;
}
