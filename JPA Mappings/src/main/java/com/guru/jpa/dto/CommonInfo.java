package com.guru.jpa.dto;

import jakarta.validation.constraints.NotNull;

public record CommonInfo(
        @NotNull(message = "Id cannot be null.")
        Integer id
) { }
