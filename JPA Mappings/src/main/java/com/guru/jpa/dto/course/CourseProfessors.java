package com.guru.jpa.dto.course;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CourseProfessors(

        @NotNull(message = "Course Id cannot be null.")
        Integer courseId,

        @NotEmpty(message = "Professors Id cannot be null or empty.")
        List<Integer> professorsId
) { }
