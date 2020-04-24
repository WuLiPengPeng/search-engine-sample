package com.wlp.knowledgepower.dao;

import com.wlp.knowledgepower.model.Permission;
import com.wlp.knowledgepower.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wlp
 * @create: 2020-02-29 14:41
 * @description: 用户表Dao层
 **/
@Repository
public interface UserMapper {

    /** 根据用户名查询用户*/
    User selectUserByUsername(String userName);

    /**根据用户名查询用户权限*/
    List<Permission> selectPermissionByUsername(String userName);
}
