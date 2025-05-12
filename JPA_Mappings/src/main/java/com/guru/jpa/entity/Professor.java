package com.guru.jpa.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Professor
{
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "profileId")
    @MapsId
    private Profile profile;

    @ManyToMany(mappedBy = "professors")
    private Set<Course> courses;

    @ManyToOne
    @JoinColumn(name = "professors")
    private Department department;
}
