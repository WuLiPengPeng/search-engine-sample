package com.wlp.knowledgepower.model;

/**
 * @author: wlp
 * @create: 2020-03-03 14:14
 * @description: 角色
 **/
public class Role {

    //角色Id
    private Integer id ;

    //角色名称
    private String roleName ;

    //角色描述
    private String roleDesc ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
