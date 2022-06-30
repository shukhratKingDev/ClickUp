package com.company.clickup.entity;

import com.company.clickup.entity.enums.TaskPermission;
import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUser extends AbstractUUIDEntity {
    @OneToMany(fetch = FetchType.LAZY)
    private List<Project> project;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> userId;
    private TaskPermission taskPermission;


}
