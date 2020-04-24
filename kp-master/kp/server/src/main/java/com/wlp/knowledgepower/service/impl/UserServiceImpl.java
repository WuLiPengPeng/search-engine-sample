package com.wlp.knowledgepower.service.impl;

import com.wlp.knowledgepower.dao.UserMapper;
import com.wlp.knowledgepower.model.Permission;
import com.wlp.knowledgepower.model.User;
import com.wlp.knowledgepower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wlp
 * @create: 2020-02-29 14:40
 * @description: 用户表服务层实现
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper ;

    @Override
    public User selectUserByUsername(String userName) {
        return userMapper.selectUserByUsername(userName);
    }

    @Override
    public List<Permission> selectPermissionByUsername(String userName) {
        return userMapper.selectPermissionByUsername(userName);
    }
}
