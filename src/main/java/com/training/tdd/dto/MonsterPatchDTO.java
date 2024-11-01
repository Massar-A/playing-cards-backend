package com.training.tdd.dto;

import com.training.tdd.model.Type;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class MonsterPatchDTO {
    private String name;
    private String image;
    private Type type;

    @DecimalMin("1")
    @DecimalMax("200")
    private Integer hp;

    private String figureCaption;

    private String attackName;
    @DecimalMin("1")
    @DecimalMax("99")
    private Integer attackStrength;
    private String attackDescription;
}
