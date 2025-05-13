package com.guru.jpa.dto.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRegister(

    @NotBlank(message = "Course code cannot be null or blank.")
    String courseCode,

    @NotBlank(message = "Course Name cannot be null or blank.")
    String courseName,

    @NotNull(message = "Has Practicals cannot be null.")
    Boolean hasPracticals,

    @NotNull(message = "Credits cannot be null.")
    @Min(0)
    @Max(5)
    Integer credits,

    @NotNull(message = "Department Id cannot be null.")
    Integer deptId
){ }
