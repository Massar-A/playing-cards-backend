package com.training.tdd.dto;

import com.training.tdd.model.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MonsterCreationDTO {
    @NotNull
    private String name;
    @NotNull
    private String image;

    @NotNull
    private Type type;

    @NotNull
    private int hp;

    @NotNull
    private String figureCaption;

    private String attackName;
    private int attackStrength;
    private String attackDescription;
}
