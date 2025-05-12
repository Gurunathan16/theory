package com.guru.jpa.dto;

public record CourseRequest(
    String courseCode,

    String courseName,

    Boolean hasPracticals,

    Integer credits,

    Integer deptId
){ }
