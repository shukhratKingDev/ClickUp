package com.company.clickup.entity;

import com.company.clickup.entity.enums.Type;
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
public class Status extends AbstractLongEntity {
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Space> spaceId;
    @OneToMany(fetch = FetchType.LAZY)
  private List<Project> projectId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String color;
    @Enumerated(EnumType.STRING)
    private Type type;

}
