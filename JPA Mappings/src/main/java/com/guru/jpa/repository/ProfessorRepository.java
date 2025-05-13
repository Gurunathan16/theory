package com.guru.jpa.repository;

import com.guru.jpa.dto.ProfessorNameWithDesignationAndQualification;
import com.guru.jpa.dto.ProfessorNameWithDesignationAndQualification;
import com.guru.jpa.entity.Professor;
import com.guru.jpa.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>
{

    @Query("SELECT new com.guru.jpa.dto.ProfessorNameAndQualification(pr.name, p.designation, pr.educationQualifications) FROM " + "Professor AS p JOIN Profile AS pr WHERE p.id = pr.id")
    Optional<ProfessorNameWithDesignationAndQualification> findProfessorNameAndQualification(Integer id);
}
