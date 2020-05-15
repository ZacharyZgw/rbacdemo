package com.mapper;

import com.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    int deleteById(Integer id);

    int insert(Role role);

    int insertSelective(Role role);

    Role selectById(Integer id);

    int updateByIdSelective(Role role);

    int updateById(Role role);

}
