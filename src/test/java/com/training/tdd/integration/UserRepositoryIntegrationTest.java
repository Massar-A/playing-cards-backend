package com.training.tdd.integration;

import com.training.tdd.model.User;
import com.training.tdd.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(UserService.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService.deleteAll();
    }

    @Test
    public void testSaveAndFindUser(){
        User user = new User("massar", "massar abbas");

        userService.createUser(user);

        Optional<User> foundUser = userService.findById(user.getId());

        assertTrue(foundUser.isPresent(), "L'utilisateur doit être trouvé");
        assertEquals("massar abbas", foundUser.get().getName(), "le nom d'utilisateur doit correspondre");
    }

}
