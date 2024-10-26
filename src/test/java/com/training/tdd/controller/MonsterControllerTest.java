package com.training.tdd.controller;

import com.training.tdd.model.Monster;
import com.training.tdd.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(MonsterController.class)
@AutoConfigureMockMvc
class MonsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    List<Monster> posts = new ArrayList<>();
    User user = new User();

    String post = "{\"user\": 10,\"id\": 100,\"title\": \"at nam consequatur ea labore ea harum\",\"body\": \"cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut\"}";

    @BeforeEach
    public void setUp() {
        // create some posts

    }

    //REST API

    // LIST
    @Test
    public void shouldFindAllPosts() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSavePost() throws Exception {
        mockMvc.perform(post("/posts").contentType("application/json").content(post))
                .andExpect(status().isCreated());
    }
}
