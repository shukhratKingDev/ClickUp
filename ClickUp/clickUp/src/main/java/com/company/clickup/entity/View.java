package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractLongEntity;
import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class View extends AbstractLongEntity {
    private String name;
    @ManyToOne
    private Icon iconId;
}
