package com.company.clickup.entity;

import com.company.clickup.entity.enums.WorkspacePermissionName;
import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.security.Permission;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspacePermission extends AbstractUUIDEntity {
    @ManyToOne
    private WorkspaceRole workspaceRole;
    @Enumerated(EnumType.STRING)
    private WorkspacePermissionName permissionName;

}
