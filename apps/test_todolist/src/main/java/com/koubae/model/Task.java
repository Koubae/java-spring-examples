package com.koubae.model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Task {
    private Long id;
    private String name;
    private String description;
    private boolean completed = false;
    private String created;
    private String updated;

    public Task(String name) {
        this.name = name;
    }
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Task(Long id, String name, String description, boolean completed, String created, String updated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public JSONObject toJSON() {
        Map<String, String> objectMap = new HashMap<>();

        objectMap.put("id", Long.toString(this.id));
        objectMap.put("name", this.name);
        objectMap.put("description", this.description);
        objectMap.put("completed", String.valueOf(this.completed));
        objectMap.put("created", this.created);
        objectMap.put("updated", this.updated);

        return new JSONObject(objectMap);

    }

    public JSONObject toJSONCreate() {
        Map<String, String> objectMap = new HashMap<>();

        objectMap.put("name", this.name);
        objectMap.put("description", this.description);

        return new JSONObject(objectMap);
    }

}
