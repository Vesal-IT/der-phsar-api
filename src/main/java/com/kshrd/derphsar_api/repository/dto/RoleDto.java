package com.kshrd.derphsar_api.repository.dto;

import org.springframework.security.core.GrantedAuthority;

public class RoleDto implements GrantedAuthority {
    private int id;
    private String roleId;
    private String name;

    public RoleDto(){}

    public RoleDto(int id, String roleId, String name) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
