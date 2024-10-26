package com.training.tdd.dto;

import com.training.tdd.model.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class MonsterOutDTO {
    private String id;
    private String name;
    private String image;
    private Type type;
    private int hp;
    private String figureCaption;
    private String attackName;
    private int attackStrength;
    private String attackDescription;
    private List<String> users;
}
