package com.guru.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courseCode;

    private String courseName;

    private Boolean hasPracticals;

    private Integer credits;

    @ManyToMany
    @JoinTable(name = "courseProfessor", joinColumns = @JoinColumn(name = "courseId"), inverseJoinColumns =
    @JoinColumn(name = "professorId"))
    private Set<Professor> professors;

    @ManyToOne
    @JoinColumn(name = "courses")
    private Department department;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
