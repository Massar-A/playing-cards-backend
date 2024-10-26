package com.training.tdd.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Field("username")
    private String username;

    @Field("name")
    private String name;

    @Field("monsters")
    private List<String> monsters = new ArrayList<>();

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public User() {}
}
