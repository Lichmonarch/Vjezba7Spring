package com.example.herewegoagain.Review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = com.example.herewegoagain.Review.ReviewController.class)
@ComponentScan(basePackages = {"com.example.herewegoagain"})
@AutoConfigureMockMvc
class ReviewControllerTest {

    private String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDcxNjA1MywiaWF0IjoxNjU0MTExMjUzLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.t9fXduLi9sivGeOGpIeGWejTwyMYIYshYIogDxiEUzHPo8NoBffYNyhrGBszX843KxZPuR8LfQS9j1WWWGBjJQ";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllReviews() throws Exception{
        this.mockMvc.perform(
                        get("/review")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf()).header(HttpHeaders.AUTHORIZATION,"Bearer " + adminToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getAllReviewsByHardware() throws Exception{
        this.mockMvc.perform(
                        get("/review/2")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf()).header(HttpHeaders.AUTHORIZATION,"Bearer " + adminToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}