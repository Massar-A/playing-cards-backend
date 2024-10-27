package com.training.tdd.config;

import com.training.tdd.mapper.MonsterMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public MonsterMapper monsterMapper() {
        return Mappers.getMapper(MonsterMapper.class);
    }
}

