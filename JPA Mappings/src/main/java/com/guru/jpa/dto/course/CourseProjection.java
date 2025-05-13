package com.guru.jpa.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseProjection
{
    private String courseCode;

    private String courseName;

    private Boolean hasPracticals;

    private Integer credits;

    private String departmentName;

    private List<String> professorsNameWithDesignation;

    private List<String> professorsQualification;

}
