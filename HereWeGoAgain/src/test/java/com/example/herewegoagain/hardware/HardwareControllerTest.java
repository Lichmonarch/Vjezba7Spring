package com.example.herewegoagain.hardware;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = com.example.herewegoagain.hardware.HardwareController.class)
@ComponentScan(basePackages = {"com.example.herewegoagain"})
@AutoConfigureMockMvc
class HardwareControllerTest {

    private String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDcxNjA1MywiaWF0IjoxNjU0MTExMjUzLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.t9fXduLi9sivGeOGpIeGWejTwyMYIYshYIogDxiEUzHPo8NoBffYNyhrGBszX843KxZPuR8LfQS9j1WWWGBjJQ";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAllHardware() throws Exception{
        this.mockMvc.perform(
                        get("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf()).header(HttpHeaders.AUTHORIZATION,"Bearer " + adminToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

    }

    @Test
    void findHardwareByCode() throws Exception{
        this.mockMvc.perform(
                        get("/hardware/1")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf()).header(HttpHeaders.AUTHORIZATION,"Bearer " + adminToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Transactional
    @Test
    void save() throws Exception {

        HardwareCommand hardwareCommand = new HardwareCommand();
        hardwareCommand.setIndex("5");
        hardwareCommand.setName("ime");
        hardwareCommand.setType("CPU");
        hardwareCommand.setPrice(100.0);
        hardwareCommand.setInStock(10);

        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,"Bearer " + adminToken)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.index").value("5"))
                .andExpect(jsonPath("$.name").value("ime"))
                .andExpect(jsonPath("$.price").value("100.0"));
    }

    @Transactional
    @Test
    void update() throws Exception {

        HardwareCommand hardwareCommand = new HardwareCommand();
        hardwareCommand.setName("ime");
        hardwareCommand.setType("CPU");
        hardwareCommand.setPrice(100.0);
        hardwareCommand.setInStock(10);

        this.mockMvc.perform(
                        put("/hardware/3")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,"Bearer " + adminToken)
                )
                .andExpect(status().isBadRequest());
    }
}