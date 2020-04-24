package com.wlp.knowledgepower.service;

import com.wlp.knowledgepower.model.Permission;
import com.wlp.knowledgepower.model.User;

import java.util.List;

/**
 * @author: wlp
 * @create: 2020-02-29 14:36
 * @description: 用户表服务层
 **/
public interface UserService {

    User selectUserByUsername(String userName);

    List<Permission> selectPermissionByUsername(String userName);
}
