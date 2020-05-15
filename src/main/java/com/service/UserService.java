package com.service;

import com.entity.User;
import com.entity.UserRolePermission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User selectUserById(Integer userId);

    List<User> selectAllUser();

    User selectUserByName(String userName);

    List<UserRolePermission> selectUrl(String userName);

    List<UserRolePermission> selectAllUrl();
}
