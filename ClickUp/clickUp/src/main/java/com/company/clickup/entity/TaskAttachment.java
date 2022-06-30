package com.company.clickup.entity;

import com.company.clickup.entity.template.AbstractLongEntity;
import com.company.clickup.entity.template.AbstractUUIDEntity;
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
public class TaskAttachment extends AbstractUUIDEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment attachment;
    private boolean pinCoverImage;

}
