package com.koubae.todolist.services;

import com.koubae.todolist.entity.Task;
import com.koubae.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class TaskService {
    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task getById(Long id) {
        Optional<Task> tasks = taskRepository.findById(id);
        if (tasks.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, String.format("Task with id %d not found", id));
        }
        return tasks.get();
    }

    public Task getByName(String name) {
        Optional<Task> tasks = taskRepository.findByName(name);
        if (tasks.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, String.format("Task with name %s not found", name));
        }
        return tasks.get();
    }

    public List<Task> list() {
        return (List<Task>) taskRepository.findAll();
    }

    public List<Task> listCompleted() {
        return taskRepository.findAllByCompletedTrue();
    }

    public List<Task> listTodo() {
        return taskRepository.findAllByCompletedFalse();
    }

    public Task update(Long id, Task taskNew) {
        Task taskOld = this.getById(id);
        taskNew.setId(id);
        taskNew.setCreated(taskOld.getCreated());
        return taskRepository.save(taskNew);
    }

    public boolean setComplete(Long id) {
        Task task = this.getById(id);
        task.setCompleted(true);
        taskRepository.save(task);
        return true;
    }

    public boolean setTodo(Long id) {
        Task task = this.getById(id);
        task.setCompleted(false);
        taskRepository.save(task);
        return true;
    }

    public boolean delete(Long id) {
        this.getById(id);
        taskRepository.deleteById(id);
        return true;
    }

}
