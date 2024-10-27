package com.training.tdd.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Field("username")
    @Indexed(unique = true)
    private String username;

    @Field("name")
    private String name;

    @Field("monsters")
    @Builder.Default
    private List<String> monsters = new ArrayList<>();

}
