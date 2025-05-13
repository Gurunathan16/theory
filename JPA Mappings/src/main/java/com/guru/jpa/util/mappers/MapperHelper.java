package com.guru.jpa.util.mappers;

import com.guru.jpa.dto.course.CourseEdit;
import com.guru.jpa.dto.course.CourseProjection;
import com.guru.jpa.dto.course.CourseRegister;
import com.guru.jpa.dto.dept.DepartmentProjection;
import com.guru.jpa.dto.dept.DeptRegister;
import com.guru.jpa.dto.dept.DeptUpdate;
import com.guru.jpa.entity.Course;
import com.guru.jpa.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MapperHelper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professors", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "department", source = "departmentObject")
    Course courseRequestToCourse(CourseRegister courseRegister, Department departmentObject);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professors", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "department", ignore = true)
    void courseUpdateToCourse(@MappingTarget Course course, CourseEdit courseEdit);

    @Mapping(target = "professors", ignore = true)
    @Mapping(target = "departmentName", ignore = true)
    CourseProjection courseToCourseProjection(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "professors", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Department deptRegisterToDept(DeptRegister deptRegister);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "professors", ignore = true)
    @Mapping(target = "courses", ignore = true)
    void deptUpdateToDepartment(@MappingTarget Department department, DeptUpdate deptUpdate);

    DepartmentProjection deptToDeptProjection(Department department);
    List<DepartmentProjection> deptToDeptProjection(List<Department> department);
}
