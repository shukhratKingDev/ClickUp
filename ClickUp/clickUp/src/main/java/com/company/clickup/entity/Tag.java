package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends AbstractLongEntity {
    private String name;
    private String color;
    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;
}
