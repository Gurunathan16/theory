package com.guru.jpa.controller;

import com.guru.jpa.dto.CommonInfo;
import com.guru.jpa.dto.dept.DeptRegister;
import com.guru.jpa.dto.dept.DeptUpdate;
import com.guru.jpa.service.DeptService;
import com.guru.jpa.util.response.ResponseEntityHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController
{
    private final DeptService deptService;

    public DeptController(DeptService deptService)
    {
        this.deptService = deptService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> deptAdd(@Valid @RequestBody DeptRegister deptRegister,
                                                    BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return deptService.addDept(deptRegister);
    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> deptEdit(@Valid @RequestBody DeptUpdate deptUpdate,
                                                       BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return deptService.editDept(deptUpdate);
    }

    @GetMapping("/view")
    public ResponseEntity<Map<String, Object>> deptView()
    {
        return deptService.viewDept();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deptDelete(@Valid @RequestBody CommonInfo commonInfo,
                                                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return ResponseEntityHandler.validationErrorBuilder(bindingResult);

        return deptService.deleteDept(commonInfo);
    }
}
