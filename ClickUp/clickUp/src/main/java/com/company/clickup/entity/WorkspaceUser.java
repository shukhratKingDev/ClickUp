package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkspaceUser extends AbstractUUIDEntity {
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private WorkspaceRole workspaceRole;
   @Column(nullable=false)
    private Timestamp dateInvited;
    private Timestamp dateJoined;

}
