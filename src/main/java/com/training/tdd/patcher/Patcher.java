package com.training.tdd.patcher;

import com.training.tdd.dto.MonsterPatchDTO;
import com.training.tdd.model.Monster;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {

    public void patchMonster(Monster targetMonster, MonsterPatchDTO patchData) throws IllegalAccessException {
        // Obtenir tous les champs de la classe MonsterPatchDTO
        Field[] patchFields = MonsterPatchDTO.class.getDeclaredFields();

        for (Field patchField : patchFields) {
            patchField.setAccessible(true); // Accéder aux champs privés

            Object patchValue = patchField.get(patchData);
            if (patchValue != null) { // Si la valeur du champ n'est pas nulle, on applique le patch
                Field targetField;
                try {
                    targetField = Monster.class.getDeclaredField(patchField.getName());
                    targetField.setAccessible(true);
                    targetField.set(targetMonster, patchValue); // Mettre à jour la valeur dans le Monster cible
                } catch (NoSuchFieldException e) {
                    // Gestion de cas où le champ n'existe pas dans la cible (si structure différente)
                    System.out.println("Champ introuvable dans Monster : " + patchField.getName());
                }
            }

            patchField.setAccessible(false); // Remet le champ en privé
        }
    }
}
