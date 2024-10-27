package com.training.tdd.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MonsterModelTest {

    @Test
    public void testMonsterCreationWithMandatoryFields() throws Exception {
        Monster monster = Monster.builder()
                .name("Draco")
                .hp(100)
                .type(Type.FIRE)
                .image("/img/draco.png")
                .figureCaption("N°20 DRACO")
                .build();

        // Vérification des valeurs initialisées
        assertEquals("Draco", monster.getName(), "Le nom du monstre ne correspond pas");
        assertEquals(100, monster.getHp(), "Les points de vie du monstre ne correspondent pas");
        assertEquals(Type.FIRE, monster.getType(), "Le type du monstre ne correspond pas");

    }

    @Test
    public void testMonsterUserIdsInitialization() {
        // Création d'un monstre
        Monster monster = Monster.builder().build();

        // Vérification que la liste d'IDs de users est initialisée vide
        assertNotNull(monster.getUsers(), "La liste des utilisateurs ne doit pas être nulle");
        assertEquals(0, monster.getUsers().size(), "La liste des utilisateurs doit être vide");
    }

    @Test
    public void testAddUserToMonster() {
        // Création d'un monstre
        Monster monster = Monster.builder().build();

        // Ajout d'un ID de User
        String userId = "user1";
        monster.getUsers().add(userId);

        // Vérification que l'ID de l'utilisateur est bien ajouté
        assertEquals(1, monster.getUsers().size(), "La liste devrait contenir un utilisateur");
        assertEquals(userId, monster.getUsers().getFirst(), "L'ID de l'utilisateur ne correspond pas");
    }
}
