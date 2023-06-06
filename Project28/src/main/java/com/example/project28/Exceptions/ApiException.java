package com.example.project28.Exceptions;

import lombok.AllArgsConstructor;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
