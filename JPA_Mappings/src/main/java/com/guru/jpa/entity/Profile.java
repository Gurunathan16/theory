package com.guru.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Profile
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Student.GENDER gender;

    public enum GENDER
    {
        MALE, FEMALE, OTHER
    }

    @Embedded
    private Address address;

    private Integer experience;

    @OneToOne(mappedBy = "profile")
    private Professor professorDetails;

}
