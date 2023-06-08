package com.koubae.todolist;

import com.koubae.todolist.app.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class App3TodolistApplicationTests {
    @LocalServerPort
    int port;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testFindAll() throws Exception {
        taskRepository.findAll();
    }

}
