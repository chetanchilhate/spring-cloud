package com.cj.department.controller;

import com.cj.department.entity.Department;
import com.cj.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
@Slf4j
public class DepartmentController {

    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Department> findAllDepartments() {
        log.info("inside findAllDepartments");
        return service.getDepartments();
    }


    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Long id) {
        log.info("inside findDepartmentById");
        return service.getDepartmentById(id);
    }


    @PostMapping("/")
    public Department createDepartment(@RequestBody Department department) {
        log.info("inside createDepartment");
        return service.saveDepartment(department);
    }

}
