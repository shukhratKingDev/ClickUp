package com.company.clickup.entity.enums;

import java.util.List;

public enum WorkspacePermissionName {
    CAN_ADD_OR_REMOVE_MEMBER("Add/Remove member","Gives user permission to add or remove"),
    CAN_MANAGE_STATUS("","");

    private String name;
    private String description;
    private List<WorkspaceRoleName> roleNames;

    WorkspacePermissionName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
