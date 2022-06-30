package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractUUIDEntity;
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
@AllArgsConstructor
@NoArgsConstructor
public class TaskUser extends AbstractUUIDEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
