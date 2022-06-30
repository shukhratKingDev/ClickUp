package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractLongEntity {
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Project> projectId;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    private boolean archived;
    private String color;
}
