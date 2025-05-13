package com.guru.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course
{
    @Id
    private String courseCode;

    private String courseName;

    private Boolean hasPracticals;

    private Integer credits;

    @ManyToMany
    @JoinTable(name = "courseProfessor", joinColumns = @JoinColumn(name = "courseId"), inverseJoinColumns =
    @JoinColumn(name = "professorId"))
    private Set<Professor> professors;

    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @CreatedDate
    private LocalDateTime createdAt;
}
