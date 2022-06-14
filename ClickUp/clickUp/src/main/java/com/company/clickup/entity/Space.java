package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbstractUUIDEntity {
    private String name;
    private String color;
    private Long workspaceId;
    private String initialLetter;
    @ManyToOne
    private Icon iconId;
    private Long avatarId;
    @ManyToOne
    private User ownerId;
    private AccessType accessType;
}
