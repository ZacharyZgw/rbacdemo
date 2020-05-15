package com.mapper;
import com.entity.User;
import com.entity.UserRolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteById(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectById(Integer id);

    int updateByIdSelective(User uesr);

    int updateById(User user);

    List<User> selectAllUser();

    User selectByName(String userName);

    List<UserRolePermission> findAuthByName(String userName);

    List<UserRolePermission> findAllAuth();
}
