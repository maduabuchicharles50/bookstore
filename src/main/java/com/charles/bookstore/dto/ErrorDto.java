package com.charles.bookstore.dto;

import java.util.HashMap;
import java.util.Map;

public class ErrorDto {
    public String type;
    public String description;
    public Map<String, String> fieldErrors = new HashMap<>();

    public ErrorDto(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
