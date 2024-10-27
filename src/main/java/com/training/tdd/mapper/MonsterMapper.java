package com.training.tdd.mapper;

import com.training.tdd.dto.MonsterCreationDTO;
import com.training.tdd.dto.MonsterOutDTO;
import com.training.tdd.model.Monster;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonsterMapper {
    MonsterMapper INSTANCE = Mappers.getMapper(MonsterMapper.class);

    Monster fromCreationtoMonster(MonsterCreationDTO monsterCreationDTO);

    MonsterOutDTO toMonsterOutDTO(Monster monster);

    List<MonsterOutDTO> toMonsterOutDTOs(List<Monster> monsters);
}
