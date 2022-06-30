package com.company.clickup.entity;

import com.company.clickup.entity.enums.TaskPermission;
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
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUser extends AbstractUUIDEntity {
private String name;
@ManyToOne(fetch = FetchType.LAZY)
private Category category;
@ManyToOne(fetch = FetchType.LAZY)
private User user;
private TaskPermission taskPermission;
}
