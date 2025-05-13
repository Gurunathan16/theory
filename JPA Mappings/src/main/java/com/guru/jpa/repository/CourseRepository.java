package com.guru.jpa.repository;

import com.guru.jpa.entity.Course;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>
{
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Course AS c WHERE LOWER(c.courseName) = LOWER" +
            "(:courseName)")
    boolean existsByCourseName(String courseName);

    @Query("DELETE FROM Course AS c WHERE c.id = :id")
    Integer deleteThruId(Integer id);

    List<Course> findAllByDepartment_DeptName(String deptName, Pageable pageable);
}
