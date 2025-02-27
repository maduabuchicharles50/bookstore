package com.charles.bookstore.exception;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(Class<?> clazz, Long id) {
        super("Cannot find " + clazz.getSimpleName() + " with id: " + id);
    }
}
