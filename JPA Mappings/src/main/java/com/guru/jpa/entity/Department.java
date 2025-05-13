package com.guru.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deptName;

    private Integer createdYear;

    @OneToMany(mappedBy = "department")
    private Set<Student> students;

    @OneToMany(mappedBy = "department")
    private Set<Professor> professors;

    @OneToMany(mappedBy = "department")
    private Set<Course> courses;



}
