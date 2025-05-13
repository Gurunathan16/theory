package com.guru.jpa.service;

import com.guru.jpa.dto.*;
import com.guru.jpa.dto.course.CourseEdit;
import com.guru.jpa.dto.course.CourseProfessors;
import com.guru.jpa.dto.course.CourseProjection;
import com.guru.jpa.dto.course.CourseRegister;
import com.guru.jpa.entity.*;
import com.guru.jpa.repository.CourseRepository;
import com.guru.jpa.repository.DeptRepository;
import com.guru.jpa.repository.ProfessorRepository;
import com.guru.jpa.util.mappers.MapperHelper;
import com.guru.jpa.util.response.ResponseEntityHandler;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService
{
    private final DeptRepository deptRepository;
    private final MapperHelper mapperHelper;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    public CourseService(DeptRepository deptRepository, MapperHelper mapperHelper, CourseRepository courseRepository, ProfessorRepository professorRepository)
    {
        this.deptRepository = deptRepository;
        this.mapperHelper = mapperHelper;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    public ResponseEntity<Map<String, Object>> addCourse(CourseRegister courseRegister)
    {
        if(courseRepository.existsByCourseName(courseRegister.courseName()))
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Course with the same name already exists", "Recovery", "Check course details once.");

        Department department = deptRepository.findById(courseRegister.deptId()).orElse(null);

        if(department == null)
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Department not found.", "Recovery", "Try adding course to an existing department or create a new department.");

        Course course = mapperHelper.courseRequestToCourse(courseRegister, department);

        courseRepository.save(course);

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Course added successfully.",
            "Details", course.getCourseName());
    }

    public ResponseEntity<Map<String, Object>> mapProfessors(CourseProfessors courseProfessors)
    {
        Course course = getCourse(courseProfessors.courseId());

        if(course == null)
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "No course found with that Id.",
                    "Recovery", "Recheck course id.");

        Set<Professor> professors = new HashSet<>();

        for(Integer id : courseProfessors.professorsId())
        {
            Optional<Professor> professor = professorRepository.findById(id);

            if(professor.isEmpty())
                return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "No professor found with that Id.", "Recovery", "Recheck professor id.");

            professors.add(professor.get());
        }

        course.setProfessors(professors);
        courseRepository.save(course);

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK,
                "Professor assigned to the " + course.getCourseName() + " successfully.",
                "Details", course.getCourseName());
    }

    public ResponseEntity<Map<String, Object>> editCourse(CourseEdit courseEdit)
    {
        Course course = courseRepository.findById(courseEdit.id()).orElse(null);

        if(course == null)
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "No course found with that Id.",
                    "Recovery", "Recheck course id.");

        mapperHelper.courseUpdateToCourse(course, courseEdit);

        courseRepository.save(course);

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Course details updated successfully.",
                "Details", course.getCourseName());
    }

    public ResponseEntity<Map<String, Object>> viewCourse(CommonInfo commonInfo)
    {
        Course course = getCourse(commonInfo.id());

        if(course == null)
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Course Id not found.", "Recovery", "Check your course id again.");

        CourseProjection courseProjection = getCourseProjection(course);

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Course details fetched successfully.",
                "Details", courseProjection);
    }

    public ResponseEntity<Map<String, Object>> viewCourseByDept(String deptName, Pageable pageable)
    {
        List<Course> coursesByDept = courseRepository.findAllByDepartment_DeptName(deptName, pageable);

        if(coursesByDept.isEmpty())
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Courses for this department not found.", "Recovery", "Check department name again.");

        List<CourseProjection> courseProjections = new ArrayList<>();

        for(Course course : coursesByDept)
        {
            courseProjections.add(getCourseProjection(course));
        }

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Course details by department fetched " +
                "successfully.", "Details", courseProjections);
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> deleteCourse(CommonInfo commonInfo)
    {
        Integer deletedCount = courseRepository.deleteThruId(commonInfo.id());

        if(deletedCount > 0)
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "No course found in that Id.",
                    "Recovery", "Check the course Id.");

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Course deleted successfully.",
                "Details", "/home");
    }

    private CourseProjection getCourseProjection(Course course)
    {
        CourseProjection courseProjection = mapperHelper.courseToCourseProjection(course);

        List<List<String>> professorsDetails = getProfessorDetails(course.getProfessors());
        List<String> professorsNamesWithDesignation = professorsDetails.getFirst();
        List<String> professorsQualifications = professorsDetails.get(1);

        courseProjection.setDepartmentName(course.getDepartment().getDeptName());
        courseProjection.setProfessorsNameWithDesignation(professorsNamesWithDesignation);
        courseProjection.setProfessorsQualification(professorsQualifications);

        return courseProjection;
    }

    private List<List<String>> getProfessorDetails(Set<Professor> professors)
    {
        List<String> professorsNamesWithDesignation = new ArrayList<>();
        List<String> professorsQualifications = new ArrayList<>();
        StringBuilder qualification = new StringBuilder();

        for(Professor professor : professors)
        {
            Optional<ProfessorNameWithDesignationAndQualification> profile =
                    professorRepository.findProfessorNameAndQualification(professor.getId());

            if(profile.isEmpty())
                continue;
            /*return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Professor Details not found.", "Recovery", "Check professor id.");*/

            // -------------- modify findProfessorNameAndQualification

            professorsNamesWithDesignation.add(profile.get().getName() + ", " + profile.get().getDesignation());

            for(EducationQualification education : profile.get().getEducationQualifications())
            {
                qualification.append(education.getDegree()).append("(").append(education.getFieldOfStudy()).append(")");
            }

            professorsQualifications.add(qualification.toString().replaceAll("\\)(?=.*\\))", "), "));
        }

        return List.of(professorsNamesWithDesignation, professorsQualifications);
    }

    private Course getCourse(Integer id)
    {
        return courseRepository.findById(id).orElse(null);
    }


}
