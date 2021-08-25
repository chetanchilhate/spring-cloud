package com.cj.user.client;

import com.cj.user.client.dto.Department;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class DepartmentClient {

    private RestTemplate restTemplate;

    private static final String DEPARTMENT_SERVICE_URL = "http://DEPARTMENT-SERVICE/api/v1/departments/";

    @Autowired
    public DepartmentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "departmentService", fallbackMethod = "departmentFallback")
    public Department getDepartmentById(Long departmentId)    {
        return  restTemplate.getForObject(new StringBuilder().append(DEPARTMENT_SERVICE_URL).append(departmentId).toString(),
                Department.class);
    }

    private Department departmentFallback(Long departmentId, Throwable e) {
        log.warn(departmentId + e.getMessage());
        log.warn("Department service is down.");
        return new Department();
    }

}
