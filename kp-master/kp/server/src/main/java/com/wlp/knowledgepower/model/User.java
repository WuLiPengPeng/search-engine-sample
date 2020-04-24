package com.wlp.knowledgepower.model;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.java2d.pipe.ValidatePipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author: wlp
 * @create: 2020-02-29 14:43
 * @description: 用户类
 **/
public class User implements UserDetails {

    private static final long serialVersionUID = 1860792764473759097L;
    //主键
    private Integer id ;

    //用户名
    private String username ;

    //真实姓名
    private String realName ;

    //密码
    private String password ;

    //创建时间
    private Date createTime;

    //最后登录时间
    private Date lastLoginTime ;

    //是否已启用，0否1是
    private Boolean enabled ;

    //账户是否过期
    private Boolean accountNonExpired ;

    //账户是否锁定
    private Boolean accountNonLocked ;

    //证书是否过期
    private Boolean credentialsNonExpired ;

     //角色集合
    private List<GrantedAuthority> Authorities = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }


    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }


    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.Authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


//    public static void main(String[] args){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encode = bCryptPasswordEncoder.encode("888");
//
//        System.out.println(encode);
//        System.out.println( bCryptPasswordEncoder.matches("888",encode));
//    }
}
