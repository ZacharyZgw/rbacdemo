package com.service.impl;

import com.entity.User;
import com.entity.UserRolePermission;
import com.mapper.UserMapper;
import com.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User selectUserById(Integer userId) {
        User user = userMapper.selectById(userId);
        return user;
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User selectUserByName(String userName) {
        User user = userMapper.selectByName(userName);
        return user;
    }

    @Override
    public List<UserRolePermission> selectUrl(String userName) {
        return userMapper.findAuthByName(userName);
    }

    @Override
    public List<UserRolePermission> selectAllUrl() {
        return userMapper.findAllAuth();
    }
}
