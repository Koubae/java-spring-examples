package com.koubae.todolist.app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.koubae.todolist.app.model.base.NamedEntity;

@Entity
@Table(name = "task")
public class Task extends NamedEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean completed = false;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean value) {
        this.completed = value;
    }


}
