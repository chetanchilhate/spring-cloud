package com.cj.user.controller;

import com.cj.user.entity.User;
import com.cj.user.service.UserService;
import com.cj.user.ui.UserDepartment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        log.info("inside create user");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public UserDepartment getUserDepartment(@PathVariable("id") Long userId) {
        log.info("inside get user and department");
        return userService.getUserDepartmentById(userId);
    }
}
