package com.wlp.knowledgepower.config.security;

import com.wlp.knowledgepower.model.Permission;
import com.wlp.knowledgepower.model.User;
import com.wlp.knowledgepower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wlp
 * @create: 2020-02-29 14:33
 * @description: 自定义用户认证
 **/

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }
        //查询数据库
        User user = userService.selectUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("user'%s'not exist", username));
        }

        //该用户所拥有的权限
        List<Permission> permissions = userService.selectPermissionByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Permission perm : permissions){
            grantedAuthorities.add(new SimpleGrantedAuthority(perm.getPermTag()));
        }
        //用户设置权限
        user.setAuthorities(grantedAuthorities);

        return user;
    }
}
