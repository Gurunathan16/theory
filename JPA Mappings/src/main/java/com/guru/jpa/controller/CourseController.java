package com.guru.jpa.controller;

import com.guru.jpa.dto.course.CourseEdit;
import com.guru.jpa.dto.CommonInfo;
import com.guru.jpa.dto.course.CourseProfessors;
import com.guru.jpa.dto.course.CourseRegister;
import com.guru.jpa.service.CourseService;
import com.guru.jpa.util.response.ResponseEntityHandler;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController
{
    private final CourseService courseService;

    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> courseAdd(@Valid @RequestBody CourseRegister courseRegister,
                                                         BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return courseService.addCourse(courseRegister);
    }

    @PostMapping("/professorsAdd")
    public ResponseEntity<Map<String, Object>> courseProfessorsMap(@Valid @RequestBody CourseProfessors courseProfessors, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return courseService.mapProfessors(courseProfessors);
    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> courseEdit(@Valid @RequestBody CourseEdit courseEdit,
                                                          BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return courseService.editCourse(courseEdit);
    }

    @PostMapping("/viewById")
    public ResponseEntity<Map<String, Object>> courseViewById(@Valid @RequestBody CommonInfo commonInfo)
    {
        return courseService.viewCourse(commonInfo);
    }

    @GetMapping("/viewByDept/{deptName}")
    public ResponseEntity<Map<String, Object>> courseViewByDept(@PathVariable String deptName, Pageable pageable,
                                                                BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return courseService.viewCourseByDept(deptName, pageable);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> courseDelete(@Valid @RequestBody CommonInfo commonInfo,
                                                            BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return courseService.deleteCourse(commonInfo);
    }
}
