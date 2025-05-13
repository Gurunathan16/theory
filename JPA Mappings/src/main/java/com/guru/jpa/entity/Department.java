package com.guru.jpa.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deptName;

    @OneToMany(mappedBy = "department")
    private Set<Student> students;

    @OneToMany(mappedBy = "department")
    private Set<Professor> professors;

    @OneToMany(mappedBy = "department")
    private Set<Course> courses;



}
