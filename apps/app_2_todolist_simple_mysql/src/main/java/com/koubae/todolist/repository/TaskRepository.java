package com.koubae.todolist.repository;

import com.koubae.todolist.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findByName(String name);
    List<Task> findAllByCompletedTrue();
    List<Task> findAllByCompletedFalse();

}
