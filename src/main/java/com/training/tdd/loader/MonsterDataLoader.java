package com.training.tdd.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.tdd.Monsters;
import com.training.tdd.repository.MonsterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MonsterDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MonsterDataLoader.class);
    private final ObjectMapper objectMapper;
    private final MonsterRepository monsterRepository;

    public MonsterDataLoader(ObjectMapper objectMapper, MonsterRepository monsterRepository) {
        this.objectMapper = objectMapper;
        this.monsterRepository = monsterRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(monsterRepository.count() == 0){
            String POSTS_JSON = "/data/monsters.json";
            log.info("Loading posts into database from JSON: {}", POSTS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(POSTS_JSON)) {
                Monsters response = objectMapper.readValue(inputStream, Monsters.class);
                monsterRepository.saveAll(response.posts());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }
    }

}