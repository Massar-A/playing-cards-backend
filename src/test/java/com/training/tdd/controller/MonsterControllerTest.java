package com.training.tdd.controller;

import com.training.tdd.mapper.MonsterMapper;
import com.training.tdd.mapper.MonsterMapperImpl;
import com.training.tdd.repository.MonsterRepository;
import com.training.tdd.service.MonsterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MonsterController.class)
@AutoConfigureMockMvc
@Import(MonsterMapperImpl.class)
class MonsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Utiliser MockBean pour mocker le repository
    private MonsterRepository monsterRepository;

    @MockBean // Mock pour MonsterService
    private MonsterService monsterService;

    @BeforeEach
    public void setUp() {
        // create some posts

    }

    //REST API

    // LIST
    @Test
    public void shouldFindAllMonsters() throws Exception {
        mockMvc.perform(get("/monster"))
                .andExpect(status().isOk());
    }
}
