package com.manager.modules.club.entity;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :Mikey
 * @description :
 * @create :2023-05-17 17:29:00
 */
@Getter
public enum RoleEnum {
    ADMIN("1", "超级管理员"),
    NORMAL("2", "普通管理员"),
    USER("3", "普通用户");

    private String role;

    private String desc;

    RoleEnum(String role, String desc) {
        this.role = role;
        this.desc = desc;
    }


    public static RoleEnum getAdminEnum(Set<String> ids) {
        RoleEnum roleEnum = null;
        for (String id : ids) {
            if (ADMIN.getRole().equals(id)) {
                roleEnum = ADMIN;
                break;
            } else if (NORMAL.getRole().equals(id)) {
                roleEnum = NORMAL;
                break;
            }
        }
        if (roleEnum == null) {
            roleEnum = USER;
        }
        return roleEnum;
    }

    public static Set<Long> getRoleIds() {
        Set<Long> set = new HashSet<>(3);
        for (RoleEnum value : values()) {
            set.add(Long.valueOf(value.getRole()));
        }
        return set;
    }

}
