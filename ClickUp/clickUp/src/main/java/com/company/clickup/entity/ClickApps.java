package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractLongEntity;
import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ClickApps extends AbstractLongEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Icon iconId;
}
