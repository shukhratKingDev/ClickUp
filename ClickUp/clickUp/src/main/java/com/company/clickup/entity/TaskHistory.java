package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractLongEntity;
import jdk.jfr.Timespan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TaskHistory extends AbstractLongEntity {
    @OneToOne
    private Task task;
    private String changeFieldName;
    @OneToOne
    private Task before;
    @OneToOne
    private Task after;
    @Column(updatable = false)
    private Timestamp data;
}
