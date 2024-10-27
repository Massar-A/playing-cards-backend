package com.training.tdd.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper= false)
@Document(collection = "monsters")
public class Monster {
    @Id
    @NotNull
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("image")
    private String image;

    @NotNull
    @Field("type")
    private Type type;
    @NotNull
    @Field("hp")
    private int hp;

    @NotNull
    @Field("figureCaption")
    private String figureCaption;

    @Field("attackName")
    private String attackName;

    @Field("attackStrength")
    private int attackStrength;

    @Field("attackDescription")
    private String attackDescription;

    @Field("users")
    @Builder.Default
    private List<String> users = new ArrayList<>();

}
