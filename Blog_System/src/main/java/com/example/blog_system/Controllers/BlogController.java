package com.example.blog_system.Controllers;

import com.example.blog_system.Models.Blog;
import com.example.blog_system.Models.MyUser;
import com.example.blog_system.Services.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser userId){
        return ResponseEntity.status(200).body(this.blogService.getAllBlogs(userId.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity add(@AuthenticationPrincipal MyUser userId, @Valid @RequestBody Blog blog){
        this.blogService.add(userId, blog);
        return ResponseEntity.status(200).body("blog added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser userId,@PathVariable int id, @Valid @RequestBody Blog blog){
        this.blogService.update(id, userId.getId(), blog);
        return ResponseEntity.status(200).body("blog updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id, @AuthenticationPrincipal MyUser userId){
        this.blogService.delete(id, userId.getId());
        return ResponseEntity.status(200).body("blog deleted");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable int id, @AuthenticationPrincipal MyUser userId){
        Blog blog = this.blogService.getBlogById(userId.getId(), id);
        return ResponseEntity.status(200).body(blog);
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity getById(@PathVariable String title, @AuthenticationPrincipal MyUser userId){
        Blog blog = this.blogService.getBlogByTitle(userId.getId(), title);
        return ResponseEntity.status(200).body(blog);
    }
}
