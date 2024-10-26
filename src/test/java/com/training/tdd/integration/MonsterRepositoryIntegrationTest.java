package com.training.tdd.integration;

import com.training.tdd.model.Monster;
import com.training.tdd.model.Type;
import com.training.tdd.repository.MonsterRepository;
import com.training.tdd.service.MonsterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(MonsterService.class)
public class MonsterRepositoryIntegrationTest {

    @Autowired
    private MonsterService monsterService;

    @BeforeEach
    public void setUp() {
        monsterService.deleteAllMonsters();
    }

    @Test
    public void testSaveAndFindMonster() {
        // Création d'un monstre
        Monster monster = new Monster();
        monster.setName("Rai");
        monster.setHp(100);
        monster.setType(Type.ELECTRIC);

        // Sauvegarder le monstre dans la base de données
        monsterService.saveMonster(monster);

        // Rechercher le monstre
        List<Monster> foundMonsters = monsterService.findAll();

        // Vérifier que le monstre est bien présent dans la base
        assertThat(foundMonsters).hasSize(1);
        assertThat(foundMonsters.getFirst().getName()).isEqualTo("Rai");
    }

    @Test
    public void testSaveMultiplesMonsters() {

        List<Monster> monsters = Arrays.asList(
                new Monster("Rai", "/img/rai.png", Type.ELECTRIC, 100, "n°19 RAI"),
                new Monster("Draco", "/img/draco.png", Type.FIRE, 200, "N°20 DRACO")
        );

        // Sauvegarder les monstres dans la base de données
        monsterService.saveAllMonsters(monsters);

        // Rechercher le monstre
        List<Monster> foundMonsters = monsterService.findAll();

        assertEquals(2, foundMonsters.size());
    }

    @Test
    public void testFindMonsterById() {
        Monster monster = new Monster("Magicarp", "/img/magic.png", Type.WATER, 20, "n°19 MAGI");
        Monster savedMonster = monsterService.saveMonster(monster);

        // Récupération par ID
        Optional<Monster> foundMonster = monsterService.findById(savedMonster.getId());
        assertTrue(foundMonster.isPresent(), "Le monstre doit être trouvé");
        assertEquals(savedMonster.getName(), foundMonster.get().getName(), "Le nom du monstre doit correspondre");
    }

    @Test
    public void testUpdateMonster() {
        Monster monster = new Monster("Golem", 200, Type.ROCK);
        Monster savedMonster = monsterService.saveMonster(monster);

        // Mise à jour des détails
        savedMonster.setHp(250);
        monsterService.saveMonster(savedMonster);

        // Vérification que les détails ont été mis à jour
        Optional<Monster> updatedMonster = monsterService.findById(savedMonster.getId());
        assertTrue(updatedMonster.isPresent(), "Le monstre doit être trouvé");
        assertEquals(250, updatedMonster.get().getHp(), "Les points de vie doivent être mis à jour");
    }

    @Test
    public void testDeleteMonster() {
        Monster monster = new Monster("Racaill", 90, Type.ROCK);
        Monster savedMonster = monsterService.saveMonster(monster);

        // Suppression du monstre
        monsterService.deleteMonster(savedMonster.getId());

        // Vérification que le monstre n'est plus présent
        Optional<Monster> deletedMonster = monsterService.findById(savedMonster.getId());
        assertFalse(deletedMonster.isPresent(), "Le monstre doit être supprimé");
    }


}
