package com.guru.jpa.dto.dept;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeptUpdate(

        @NotNull(message = "Department Id cannot be null.")
        Integer id,

        @NotBlank(message = "Department Name cannot be null or blank.")
        String deptName,

        @NotNull(message = "Created Year cannot be null.")
        Integer createdYear
) { }
