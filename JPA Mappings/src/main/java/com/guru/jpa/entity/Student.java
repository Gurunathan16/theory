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
public class Student
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    public enum GENDER
    {
        MALE, FEMALE, OTHER
    }

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;

    @ManyToMany
    @JoinTable(name = "studentCourses", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns =
    @JoinColumn(name = "courseId"))
    Set<Course> courses;

    @Column(unique = true, nullable = false)
    private String uniqueHash;

}
