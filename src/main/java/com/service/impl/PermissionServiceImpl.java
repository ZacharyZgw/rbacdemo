package com.service.impl;

import com.entity.Permission;
import com.mapper.PermissionMapper;
import com.service.PermissionService;
import org.springframework.stereotype.Service;
//import javax.annotation.Resource;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getAllPermission() {
        return permissionMapper.selectAllUrls();
    }
}
