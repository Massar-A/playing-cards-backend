package com.training.tdd.controller;

import com.training.tdd.dto.MonsterConverter;
import com.training.tdd.dto.MonsterOutDTO;
import com.training.tdd.model.Monster;
import com.training.tdd.service.MonsterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public record MonsterController(
        MonsterConverter monsterConverter,
        MonsterService monsterService
) {

    @GetMapping("")
    List<Monster> findAll() {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<MonsterOutDTO> createPost(@Valid @RequestBody Monster monster) {
        Monster createdMonster = monsterService.saveMonster(monster);
        MonsterOutDTO monsterOutDTO = monsterConverter.convertObjectToPostOutDTO(createdMonster);
        return new ResponseEntity<>(monsterOutDTO, HttpStatus.CREATED);
    }

}
