package com.company.clickup.entity.enums;

import lombok.Data;

import java.util.List;

public enum WorkspacePermissionName {
    CAN_ADD_OR_REMOVE_MEMBER("Add/Remove member","Gives user permission to add or remove"),
    CAN_MANAGE_STATUS("Manage status","Gives user permission to manages status");

    private String name;
    private String description;
    private List<WorkspaceRoleName> roleNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WorkspaceRoleName> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<WorkspaceRoleName> roleNames) {
        this.roleNames = roleNames;
    }

    WorkspacePermissionName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
