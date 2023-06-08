package com.koubae.todolist.app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(ControllerHome.class)
public class ControllerHomeTests {
    @Value("${app.name}")
    protected String APP_NAME;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIndex() throws Exception {
        String expected = String.format("<h1>Welcome to %s</h1>", APP_NAME);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andReturn();

        String body = response.getResponse().getContentAsString();

        assertEquals(body, expected);
    }

    @Test
    void testPing() throws Exception {
        String expected = "pong";


        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/ping"))
                .andExpect(status().isOk())
                .andReturn();

        String body = response.getResponse().getContentAsString();

        assertThat(body).isEqualTo(expected);

    }

    @Test
    void testNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/someWeirdURI")).andExpect(status().isNotFound());
    }

}
