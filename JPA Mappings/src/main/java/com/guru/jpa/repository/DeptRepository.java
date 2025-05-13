package com.guru.jpa.repository;

import com.guru.jpa.entity.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Department, Integer>
{

    boolean existsByDeptName(String deptName);

    @Query("DELETE FROM Department AS d WHERE d.id = :id")
    Integer deleteThruId(Integer id);
}
