package com.example.project4.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private int statusCode;
    private long timestamp;
}
