package com.company.clickup.entity.template;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class AbstractLongEntity extends AbstractMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
