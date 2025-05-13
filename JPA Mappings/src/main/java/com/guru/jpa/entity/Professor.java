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
public class Professor
{
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProfessorDesignation designation;

    public enum ProfessorDesignation {
        ASSISTANT_PROFESSOR,
        ASSOCIATE_PROFESSOR,
        PROFESSOR,
        HOD,
        LECTURER,
        SENIOR_LECTURER,
        DEAN,
        PRINCIPAL,
        VISITING_PROFESSOR,
        ADJUNCT_PROFESSOR
    }

    @OneToOne
    @JoinColumn(name = "profileId")
    @MapsId
    private Profile profile;

    @ManyToMany(mappedBy = "professors")
    private Set<Course> courses;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
}
