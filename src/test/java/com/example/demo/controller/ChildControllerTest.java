package com.example.demo.controller;

import com.example.demo.dto.ChildDTO;
import com.example.demo.service.ChildService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChildController.class)
class ChildControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChildService childService;

    @Test
    void getAllChildren() throws Exception {
        Mockito.when(childService.getAllChildren())
                .thenReturn(Collections.singletonList(new ChildDTO(1L, "John", 10)));

        mockMvc.perform(get("/api/v1/children")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }
}