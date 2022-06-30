package com.company.clickup.entity;

import com.company.clickup.entity.enums.AccessType;
import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbstractUUIDEntity {
    private String name;
    private String color="WHITE";// default value
    private Long workspaceId;
    private String initialLetter;
    @ManyToOne(fetch = FetchType.LAZY)
    private Icon iconId;
    private Long avatarId;
    @ManyToOne(fetch = FetchType.LAZY)
    private User ownerId;
    @Enumerated(EnumType.STRING)
    private AccessType accessType=AccessType.PUBLIC;
    @PrePersist
    @PreUpdate
    public void setInitialLetter(){
        initialLetter= name.substring(0,1);
    }
}
