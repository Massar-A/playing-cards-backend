package com.training.tdd.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
public class MonsterModelTest {

    @Test
    public void testMonsterCreationWithMandatoryFields() throws Exception {
        Monster monster = new Monster();
        monster.setName("Rai");
        monster.setHp(100);
        monster.setType(Type.ELECTRIC);

        // Vérification des valeurs initialisées
        assertEquals("Rai", monster.getName(), "Le nom du monstre ne correspond pas");
        assertEquals(100, monster.getHp(), "Les points de vie du monstre ne correspondent pas");
        assertEquals(Type.ELECTRIC, monster.getType(), "Le type du monstre ne correspond pas");

    }

    @Test
    public void testMonsterUserIdsInitialization() {
        // Création d'un monstre
        Monster monster = new Monster();

        // Vérification que la liste d'IDs de users est initialisée vide
        assertNotNull(monster.getUsers(), "La liste des utilisateurs ne doit pas être nulle");
        assertEquals(0, monster.getUsers().size(), "La liste des utilisateurs doit être vide");
    }

    @Test
    public void testAddUserToMonster() {
        // Création d'un monstre
        Monster monster = new Monster();

        // Ajout d'un ID de User
        String userId = "user1";
        monster.getUsers().add(userId);

        // Vérification que l'ID de l'utilisateur est bien ajouté
        assertEquals(1, monster.getUsers().size(), "La liste devrait contenir un utilisateur");
        assertEquals(userId, monster.getUsers().getFirst(), "L'ID de l'utilisateur ne correspond pas");
    }
}
