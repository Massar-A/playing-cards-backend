package com.training.tdd.controller;

import com.training.tdd.dto.MonsterCreationDTO;
import com.training.tdd.dto.MonsterOutDTO;
import com.training.tdd.dto.MonsterPatchDTO;
import com.training.tdd.mapper.MonsterMapper;
import com.training.tdd.model.Monster;
import com.training.tdd.patcher.Patcher;
import com.training.tdd.service.MonsterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monster")
public record MonsterController(
        MonsterMapper monsterMapper,
        MonsterService monsterService,
        Patcher patcher
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

    @PatchMapping("/{id}")
    public ResponseEntity<MonsterOutDTO> updateMonster(@PathVariable String id, @RequestBody MonsterPatchDTO monsterBody) throws IllegalAccessException {
        Optional<Monster> monster = monsterService.findById(id);
        if (monster.isPresent()) {
            Monster monsterToUpdate = monster.get();
            patcher.patchMonster(monsterToUpdate, monsterBody);
            monsterService.saveMonster(monsterToUpdate);
            return new ResponseEntity<>(monsterMapper.toMonsterOutDTO(monsterToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<MonsterOutDTO> createMonster(@Valid @RequestBody MonsterCreationDTO monster) {
        Monster monsterToCreate = monsterMapper.fromCreationtoMonster(monster);
        Monster createdMonster = monsterService.saveMonster(monsterToCreate);
        MonsterOutDTO monsterOutDTO = monsterMapper.toMonsterOutDTO(createdMonster);
        return new ResponseEntity<>(monsterOutDTO, HttpStatus.CREATED);
    }

}
