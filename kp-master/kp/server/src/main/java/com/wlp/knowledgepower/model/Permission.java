package com.wlp.knowledgepower.model;

/**
 * @author: wlp
 * @create: 2020-03-01 16:08
 * @description: 权限
 **/
public class Permission {

    //权限Id
    private Integer Id ;

    //权限名称
    private String permName;

    //权限标签
    private String permTag;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermTag() {
        return permTag;
    }

    public void setPermTag(String permTag) {
        this.permTag = permTag;
    }
}
