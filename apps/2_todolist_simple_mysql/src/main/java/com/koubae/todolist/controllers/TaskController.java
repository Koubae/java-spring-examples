package com.koubae.todolist.controllers;

import com.koubae.todolist.entity.Task;
import com.koubae.todolist.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.list());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompleted() {
        return ResponseEntity.ok(taskService.listCompleted());
    }

    @GetMapping("/todo")
    public ResponseEntity<List<Task>> getAllTodo() {
        return ResponseEntity.ok(taskService.listTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @GetMapping("/{name}/")
    public ResponseEntity<Task> getByName(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(taskService.getByName(name));
    }

    @PostMapping("")
    public ResponseEntity<Task> createTask(
            @RequestBody Task task
    ) {
        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task taskNew
    ) {
        return ResponseEntity.ok(taskService.update(id, taskNew));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Boolean> markComplete(
            @PathVariable Long id
    ) {
        if (taskService.setComplete(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);

    }

    @PutMapping("/{id}/todo")
    public ResponseEntity<Boolean> markTodo(
            @PathVariable Long id
    ) {
        if (taskService.setTodo(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(
            @PathVariable Long id
    ) {
        boolean deleted = taskService.delete(id);
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(deleted, status);
    }

}
