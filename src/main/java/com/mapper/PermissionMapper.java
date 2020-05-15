package com.mapper;

import com.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionMapper {
    int addPermission(Permission permission);
    int addPermissionSelective(Permission permission);
    int deletePermissionById(Integer id);
    int updatePermission(Permission permission);
    List<Permission> selectAllUrls();
    Permission selectById(Integer id);
}
