package com.cj.department.service;

import com.cj.department.entity.Department;
import com.cj.department.repositroy.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {

    private final DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getDepartments() {
        log.info("inside getDepartments");
        return repository.findAll();
    }

    public Department getDepartmentById(Long departmentId) {
        log.info("inside getDepartmentById");
        return repository.findById(departmentId).orElseThrow();
    }

    public Department saveDepartment(Department department) {
        log.info("inside saveDepartment");
        return repository.save(department);
    }
}
