package com.training.tdd.service;

import com.training.tdd.exception.EntityNotFoundException;
import com.training.tdd.model.Monster;
import com.training.tdd.repository.MonsterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record MonsterService(MonsterRepository monsterRepository) {

    public void deleteMonster(String id) {monsterRepository.deleteById(id);};
    public void deleteAllMonsters() {monsterRepository.deleteAll();};
    public Monster saveMonster(Monster monster) {return monsterRepository.save(monster);};
    public List<Monster> findAll() {return monsterRepository.findAll();};
    public Optional<Monster> findById(String id) {
        return monsterRepository.findById(id);};
    public List<Monster> saveAllMonsters(List<Monster> monsters) {return monsterRepository.saveAll(monsters);}
}
