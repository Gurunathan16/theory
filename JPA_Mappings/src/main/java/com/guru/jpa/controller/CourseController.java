package com.guru.jpa.controller;

import com.guru.jpa.dto.CourseRequest;
import com.guru.jpa.entity.Course;
import com.guru.jpa.entity.Department;
import com.guru.jpa.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController
{
    private final CourseService courseService;

    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }

    @PostMapping
    public String addCourse(@RequestBody CourseRequest courseRequest)
    {
        return courseService.addCourse(courseRequest);
    }
}
