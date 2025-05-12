package com.guru.jpa.service;

import com.guru.jpa.dto.CourseRequest;
import com.guru.jpa.entity.Course;
import com.guru.jpa.entity.Department;
import com.guru.jpa.repository.CourseRepository;
import com.guru.jpa.repository.DepartmentRepository;
import com.guru.jpa.util.mappers.MapperHelper;
import org.springframework.stereotype.Service;
import com.guru.jpa.util.mappers.DTOMapper;

@Service
public class CourseService
{
    private final DepartmentRepository departmentRepository;
    private final MapperHelper mapperHelper;
    private final CourseRepository courseRepository;

    public CourseService(DepartmentRepository departmentRepository, MapperHelper mapperHelper, CourseRepository courseRepository)
    {
        this.departmentRepository = departmentRepository;
        this.mapperHelper = mapperHelper;
        this.courseRepository = courseRepository;
    }

    public String addCourse(CourseRequest courseRequest)
    {
        Department department = departmentRepository.findById(courseRequest.deptId()).orElse(null);

        if(department == null)
            return "Department not found.";

        Course course = mapperHelper.courseRequestToCourse(courseRequest, department);

        courseRepository.save(course);

        return "Success";
    }
}
