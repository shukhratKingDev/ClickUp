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
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project  extends AbstractUUIDEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Space spaceId;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    private boolean archived;
    private String color;
}
