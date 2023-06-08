package com.koubae.todolist.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.koubae.todolist.app.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findByName(String name);
    List<Task> findAllByCompletedTrue();
    List<Task> findAllByCompletedFalse();

}
