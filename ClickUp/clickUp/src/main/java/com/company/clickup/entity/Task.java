package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task extends AbstractUUIDEntity {
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Status statusId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    private Priority priority;
    @Column(updatable = false)
    @LastModifiedDate
    private Timestamp  startedDateHas;
    private Timestamp dueDate;
    private Timestamp dueTimeHas;
    private long estimateTime;
    private Timestamp activeDate;





}
