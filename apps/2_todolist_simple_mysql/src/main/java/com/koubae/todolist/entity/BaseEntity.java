package com.koubae.todolist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    @CreatedDate
    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private Timestamp created;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "updated", nullable = false)
    private Timestamp updated;

    public Timestamp getCreated() { return this.created; }
    public void setCreated(Timestamp created) { this.created = created; }

    public Timestamp getUpdated() { return this.updated; }
    public void setUpdated(Timestamp updated) { this.updated = updated; }

}
