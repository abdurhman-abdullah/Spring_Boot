package com.example.blog_system.DTOs;

import com.example.blog_system.Models.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Set;
@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private Set<Blog> blogs;
}
