package com.cj.user.service;

import com.cj.user.client.DepartmentClient;
import com.cj.user.entity.User;
import com.cj.user.repository.UserRepository;
import com.cj.user.ui.UserDepartment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;

    private final DepartmentClient departmentClient;

    @Autowired
    public UserService(UserRepository repository, DepartmentClient departmentClient) {
        this.repository = repository;
        this.departmentClient = departmentClient;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public UserDepartment getUserDepartmentById(Long userId) {
        User user = repository.findById(userId).orElseThrow();
        UserDepartment userDepartment = new UserDepartment();

        userDepartment.setUser(user);
        userDepartment.setDepartment(departmentClient.getDepartmentById(user.getDepartmentId()));
        return userDepartment;
    }

    public User saveUser(User user) {
        log.info("inside save user");
        return repository.save(user);
    }

}
