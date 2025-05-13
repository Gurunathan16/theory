package com.guru.jpa.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationQualification
{
    private String degree;

    private String fieldOfStudy;

    private String university;

    private String collegeName;

    private Integer passingYear;

    private Float percentage;

    private String grade;

    private String country;

    private Boolean isHighestDegree;

    private String additionalNotes;
}
