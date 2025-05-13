package com.guru.jpa.dto.dept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentProjection
{
    private Integer id;

    private String deptName;

    private Integer createdYear;
}
