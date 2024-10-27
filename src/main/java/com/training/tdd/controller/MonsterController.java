package com.training.tdd.controller;

import com.training.tdd.dto.MonsterOutDTO;
import com.training.tdd.mapper.MonsterMapper;
import com.training.tdd.model.Monster;
import com.training.tdd.service.MonsterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monster")
public record MonsterController(
        MonsterMapper monsterMapper,
        MonsterService monsterService
) {

    @GetMapping("")
    List<MonsterOutDTO> findAll() {
        return monsterMapper.toMonsterOutDTOs(monsterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonsterOutDTO> findById(@PathVariable String id) {
        Optional<Monster> monster = monsterService.findById(id);
        if (monster.isPresent()) {
            MonsterOutDTO monsterOutDTO = monsterMapper.toMonsterOutDTO(monster.get());
            return new ResponseEntity<>(monsterOutDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<MonsterOutDTO> updateMonster(@PathVariable String id, @RequestBody MonsterCreationDTO monsterBody) {
        Optional<Monster> monster = monsterService.findById(id);
        if (monster.isPresent()) {
            monsterService.saveMonster(monsterBody);
        }
    }*/

    @PostMapping("")
    public ResponseEntity<MonsterOutDTO> createMonster(@Valid @RequestBody Monster monster) {
        Monster createdMonster = monsterService.saveMonster(monster);
        MonsterOutDTO monsterOutDTO = monsterMapper.toMonsterOutDTO(createdMonster);
        return new ResponseEntity<>(monsterOutDTO, HttpStatus.CREATED);
    }

}
