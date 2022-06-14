package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractLongEntity;
import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name","owner_id"})})
public class Workspace extends AbstractLongEntity {
    @Column(nullable = false)
    private String name;
    private String color;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User owner;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne(fetch=FetchType.LAZY)
    private Attachment avatarId;

    @PrePersist// befor persisting data
    @PreUpdate// before updating data
    public void setInitialLetter(){
        this.initialLetter=name.substring(0,1);
    }
}
