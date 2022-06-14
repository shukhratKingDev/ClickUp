package com.company.clickup.entity;

import com.company.clickup.entity.enums.WorkspaceRoleName;
import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceRole extends AbstractUUIDEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendsRole;
}
