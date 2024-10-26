package com.training.tdd.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> entityClass, String uniqueIdentifier) {
        super("The object " + uniqueIdentifier + " of class " + entityClass.getSimpleName() + " was not found.");
    }
}
