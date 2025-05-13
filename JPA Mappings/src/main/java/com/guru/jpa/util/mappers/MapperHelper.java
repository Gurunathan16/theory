package com.guru.jpa.util.mappers;

import com.guru.jpa.dto.CourseRequest;
import com.guru.jpa.entity.Course;
import com.guru.jpa.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapperHelper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professors", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "department", source = "departmentObject")
    Course courseRequestToCourse(@MappingTarget CourseRequest courseRequest, Department departmentObject);
}
