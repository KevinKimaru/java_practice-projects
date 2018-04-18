package com.kevin.core;

import javax.persistence.*;

/**
 * Created by Kevin Kimaru Chege on 3/4/2018.
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Version
    private Long version;

    protected BaseEntity() {
        id = null;
    }
}
