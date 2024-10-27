package com.training.tdd.dto;

import com.training.tdd.model.Monster;
import com.training.tdd.service.UserService;
import org.springframework.stereotype.Component;


@Component
public record UserConverter(UserService userService) {

}
