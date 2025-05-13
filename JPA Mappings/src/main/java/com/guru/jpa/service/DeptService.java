package com.guru.jpa.service;

import com.guru.jpa.dto.CommonInfo;
import com.guru.jpa.dto.dept.DepartmentProjection;
import com.guru.jpa.dto.dept.DeptRegister;
import com.guru.jpa.dto.dept.DeptUpdate;
import com.guru.jpa.entity.Department;
import com.guru.jpa.repository.DeptRepository;
import com.guru.jpa.util.mappers.MapperHelper;
import com.guru.jpa.util.response.ResponseEntityHandler;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DeptService
{
    private final DeptRepository deptRepository;
    private final MapperHelper mapperHelper;

    public DeptService(DeptRepository deptRepository, MapperHelper mapperHelper)
    {
        this.deptRepository = deptRepository;
        this.mapperHelper = mapperHelper;
    }

    public ResponseEntity<Map<String, Object>> addDept(DeptRegister deptRegister)
    {
        if(deptRepository.existsByDeptName(deptRegister.deptName()))
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Department with the same name already exists", "Recovery", "Check department details once.");

        deptRepository.save(mapperHelper.deptRegisterToDept(deptRegister));

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Department added successfully.",
                "Details", deptRegister.deptName());
    }

    public ResponseEntity<Map<String, Object>> editDept(DeptUpdate deptUpdate)
    {
        Optional<Department> departmentOptional = deptRepository.findById(deptUpdate.id());

        if(departmentOptional.isEmpty())
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Department with that Id doesn't exists", "Recovery", "Check department id once.");

        Department department = departmentOptional.get();

        mapperHelper.deptUpdateToDepartment(department, deptUpdate);

        deptRepository.save(department);

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Department updated successfully.",
                "Details", department.getDeptName());
    }

    public ResponseEntity<Map<String, Object>> viewDept()
    {
        List<Department> department = deptRepository.findAll();

        if(department.isEmpty())
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "No department found.", "Recovery", "Try adding departments.");

        List<DepartmentProjection> departmentProjections = mapperHelper.deptToDeptProjection(department);

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Departments fetched successfully.",
                "Details", departmentProjections);
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> deleteDept(CommonInfo commonInfo)
    {
        Integer deletedCount = deptRepository.deleteThruId(commonInfo.id());

        if(deletedCount > 0)
            return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "No department found in that Id.", "Recovery", "Check the department Id.");

        return ResponseEntityHandler.getResponseEntity(HttpStatus.OK, "Department deleted successfully.",
                "Details", "/home");
    }
}
