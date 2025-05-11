package com.guru.jpa.entity;

import jakarta.persistence.*;

@Entity
public class student
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @Embedded
    private Address address;

    public enum GENDER
    {
        MALE, FEMALE, OTHER
    }


}
