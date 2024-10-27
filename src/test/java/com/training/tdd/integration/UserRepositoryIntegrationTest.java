package com.training.tdd.integration;

import com.training.tdd.model.User;
import com.training.tdd.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(UserService.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        userService.deleteAll();
    }

    @Test
    public void testSaveAndFindUser(){
        User user = User.builder()
                .name("massar abbas")
                .username("massar")
                .build();

        userService.saveUser(user);

        Optional<User> foundUser = userService.findById(user.getId());

        assertTrue(foundUser.isPresent(), "L'utilisateur doit être trouvé");
        assertEquals("massar abbas", foundUser.get().getName(), "le nom d'utilisateur doit correspondre");
    }

    @Test
    public void testFindUserById(){
        User user = User.builder()
                .name("massar abbas")
                .username("massar")
                .build();
        userService.saveUser(user);
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        assertTrue(foundUser.isPresent());

        Optional<User> foundUser2 = userService.findByUsername("user");
        assertFalse(foundUser2.isPresent());
    }

    @Test
    public void testCreateAndUpdateUser() {
        User user = User.builder()
                .name("massar abbas")
                .username("massar")
                .build();
        userService.saveUser(user);

        user.setUsername("louna abbas");
        userService.saveUser(user);
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals("louna abbas", foundUser.get().getUsername(), "le nom d'utilisateur doit correspondre");
    }

    @Test
    public void testFindAllUsers() {
        User user = User.builder()
                .name("massar abbas")
                .username("massar")
                .build();
        User user1 = User.builder()
                .name("louna abbas")
                .username("louna")
                .build();
        userService.saveUser(user);
        userService.saveUser(user1);

        List<User> users = userService.findAll();

        assertEquals(2, users.size());
    }

    @Test
    public void testDeleteUser() {
        User user = User.builder()
                .name("massar abbas")
                .username("massar")
                .build();
        userService.saveUser(user);
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        assertTrue(foundUser.isPresent());
        userService.deleteUser(foundUser.get());
        Optional<User> foundUser2 = userService.findByUsername(user.getUsername());
        assertFalse(foundUser2.isPresent());
        List<User> users = userService.findAll();
        assertEquals(0, users.size());
    }

    @Test
    public void testUsernameUniqueRestriction() {
        User user = User.builder()
                .name("massar abbas")
                .username("massar")
                .build();
        userService.saveUser(user);
        assertTrue(userService.findByUsername(user.getUsername()).isPresent());

        User duplicateUser = User.builder()
                .name("massar duplicate")
                .username("massar")
                .build();

        Exception exception = assertThrows(org.springframework.dao.DuplicateKeyException.class, () -> {
            userService.saveUser(duplicateUser);
        });

        String expectedMessage = "duplicate key error";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "L'exception doit indiquer une clé en double");
    }

}
