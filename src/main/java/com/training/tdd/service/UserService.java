package com.training.tdd.service;

import com.training.tdd.model.User;
import com.training.tdd.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record UserService(UserRepository userRepository) {
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> findByUsername(String username) {return Optional.ofNullable(userRepository.findUserByUsername(username));}
    public Optional<User> findById(String id) {return userRepository.findById(id);}
    public List<User> findAll() {return userRepository.findAll();}
    public void deleteUser(User user) {userRepository.delete(user);}
    public void deleteAll() {userRepository.deleteAll();}

}
