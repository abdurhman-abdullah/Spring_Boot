package com.example.blog.ApiException;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponseApi {
    private String message;
}
