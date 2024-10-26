package com.training.tdd.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserOutDTO {
    private String id;
    private String name;
    private String username;
    private List<String> monsters;
}
