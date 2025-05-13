package com.guru.jpa.dto.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseEdit(
        @NotNull(message = "Id cannot be null.")
        Integer id,

        @NotBlank(message = "Course Name cannot be null or blank.")
        String courseName,

        @NotNull(message = "Has Practicals cannot be null.")
        Boolean hasPracticals,

        @NotNull(message = "Credits cannot be null.")
        @Min(0)
        @Max(5)
        Integer credits
) { }
