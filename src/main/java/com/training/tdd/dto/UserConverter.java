package com.training.tdd.dto;

import com.training.tdd.model.Monster;
import com.training.tdd.service.UserService;
import org.springframework.stereotype.Component;

@Component
public record UserConverter(UserService userService) {
    public MonsterOutDTO convertObjectToPostOutDTO(Monster monster) {
        MonsterOutDTO monsterOutDTO = new MonsterOutDTO();
        monsterOutDTO.setId(monster.getId());
        monsterOutDTO.setName(monster.getName());
        monsterOutDTO.setImage(monster.getImage());
        monsterOutDTO.setType(monster.getType());
        monsterOutDTO.setHp(monster.getHp());
        monsterOutDTO.setFigureCaption(monster.getFigureCaption());
        monsterOutDTO.setAttackName(monster.getAttackName());
        monsterOutDTO.setAttackStrength(monster.getAttackStrength());
        monsterOutDTO.setAttackDescription(monster.getAttackDescription());

        return monsterOutDTO;
    }
}
