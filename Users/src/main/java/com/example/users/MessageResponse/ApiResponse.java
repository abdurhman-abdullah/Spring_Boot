package com.example.users.MessageResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ApiResponse extends RuntimeException {

    public ApiResponse(String message) {
        super(message);
    }
}
