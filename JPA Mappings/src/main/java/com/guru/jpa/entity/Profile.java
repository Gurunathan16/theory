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
public class Profile
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

    @ElementCollection
    @CollectionTable(name = "professorEducationQualification", joinColumns = @JoinColumn(name = "profileId"))
    private Set<EducationQualification> educationQualifications;

    private Integer experience;

    @OneToOne(mappedBy = "profile")
    private Professor professorDuties;

    @Column(unique = true, nullable = false)
    private String uniqueHash;

}
