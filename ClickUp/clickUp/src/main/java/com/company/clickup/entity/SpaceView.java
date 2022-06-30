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
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceView  extends AbstractLongEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Space spaceId;
    @ManyToOne(fetch = FetchType.LAZY)
    private View viewId;
}
