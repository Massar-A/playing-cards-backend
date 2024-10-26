package com.training.tdd.repository;

import com.training.tdd.model.Monster;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface MonsterRepository extends MongoRepository<Monster, String> {
}
