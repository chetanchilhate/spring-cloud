package com.cj.user.ui;

import com.cj.user.client.dto.Department;
import com.cj.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDepartment {

    private User user;
    private Department department;

}
