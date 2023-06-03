package com.koubae.todolist.services;

import com.koubae.todolist.entity.Task;
import com.koubae.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {
    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

    public boolean setComplete(Long id) {
        Optional<Task> tasks = this.getById(id);
        if (tasks.isEmpty()) {
            return false;
        }
        Task task = tasks.get();
        task.setCompleted(true);
        taskRepository.save(task);
        return true;
    }

    public boolean setTodo(Long id) {
        Optional<Task> tasks = this.getById(id);
        if (tasks.isEmpty()) {
            return false;
        }
        Task task = tasks.get();
        task.setCompleted(false);
        taskRepository.save(task);
        return true;
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> getByName(String name) {
        return taskRepository.findByName(name);
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

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

}
