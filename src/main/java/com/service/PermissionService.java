package com.service;

import com.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    List<Permission> getAllPermission();

}

