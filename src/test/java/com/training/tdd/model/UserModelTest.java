package com.training.tdd.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserModelTest {
    @Test
    public void testUserCreationWithEmptyMonsters() {
        // Étape 1 : Créer un utilisateur sans monstres associés
        User user = User.builder().build();

        // Étape 3 : Vérifier que la liste de monstres est vide
        assertNotNull(user.getMonsters(), "La liste de monstres ne doit pas être nulle");
        assertEquals(0, user.getMonsters().size(), "La liste de monstres doit être vide");
    }

    @Test
    public void testAddMonsterToUser() {
        // Création d'un utilisateur
        User user = User.builder().build();

        // Ajout d'un ID de Monster
        String monsterId = "monster1";
        user.getMonsters().add(monsterId);

        // Vérification que la liste des monstres contient bien cet ID
        assertEquals(1, user.getMonsters().size(), "La liste devrait contenir un monstre");
        assertEquals(monsterId, user.getMonsters().getFirst(), "L'ID du monstre ne correspond pas");
    }

}
