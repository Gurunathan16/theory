package com.guru.jpa.dto;

import com.guru.jpa.entity.EducationQualification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorNameWithDesignationAndQualification
{
    private String name;

    private String designation;

    private Set<EducationQualification> educationQualifications;

}
