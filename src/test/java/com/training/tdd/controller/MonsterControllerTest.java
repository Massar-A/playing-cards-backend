package com.training.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.tdd.dto.MonsterCreationDTO;
import com.training.tdd.dto.MonsterPatchDTO;
import com.training.tdd.mapper.MonsterMapper;
import com.training.tdd.mapper.MonsterMapperImpl;
import com.training.tdd.model.Monster;
import com.training.tdd.model.Type;
import com.training.tdd.patcher.Patcher;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MonsterController.class)
@AutoConfigureMockMvc
@Import({MonsterMapperImpl.class, Patcher.class})
class MonsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Utiliser MockBean pour mocker le repository
    private MonsterRepository monsterRepository;

    @MockBean // Mock pour MonsterService
    private MonsterService monsterService;

    @Autowired
    private ObjectMapper objectMapper; // Injecte ObjectMapper pour la conversion en JSON


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

    @Test
    public void shouldCreateAndFindMonster() throws Exception {
        MonsterCreationDTO monsterCreationDTO = MonsterCreationDTO.builder()
                .name("Cara")
                .hp(50)
                .type(Type.WATER)
                .figureCaption("N°21 CARA")
                .image("/img/cara.png")
                .build();

        String jsonRequest = objectMapper.writeValueAsString(monsterCreationDTO);
        mockMvc.perform(post("/monster")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldGetMonsterById() throws Exception {
        // Créer un monstre factice pour le retour du service
        Monster monster = Monster.builder()
                .id("wshwshwsh")
                .name("Cara")
                .hp(50)
                .type(Type.WATER)
                .figureCaption("N°21 CARA")
                .image("/img/cara.png")
                .build();

        // Configurer le mock pour retourner ce monstre
        when(monsterService.findById("wshwshwsh")).thenReturn(Optional.of(monster));

        mockMvc.perform(get("/monster/wshwshwsh")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cara"))
                .andExpect(jsonPath("$.hp").value(50))
                .andExpect(jsonPath("$.type").value("WATER"))
                .andExpect(jsonPath("$.figureCaption").value("N°21 CARA"))
                .andExpect(jsonPath("$.image").value("/img/cara.png"));
    }

    @Test
    public void shouldUpdateMonster() throws Exception {
        Monster monster = Monster.builder()
                .id("wshwshwsh")
                .name("Cara")
                .hp(50)
                .type(Type.WATER)
                .figureCaption("N°21 CARA")
                .image("/img/cara.png")
                .build();

        MonsterPatchDTO monsterPatchDTO = MonsterPatchDTO.builder()
                .hp(75)
                .build();
        // Configurer le mock pour retourner ce monstre
        when(monsterService.findById("wshwshwsh")).thenReturn(Optional.of(monster));

        mockMvc.perform(patch("/monster/wshwshwsh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monsterPatchDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cara"))
                .andExpect(jsonPath("$.hp").value(75))
                .andExpect(jsonPath("$.type").value("WATER"))
                .andExpect(jsonPath("$.figureCaption").value("N°21 CARA"))
                .andExpect(jsonPath("$.image").value("/img/cara.png"));
    }
}
