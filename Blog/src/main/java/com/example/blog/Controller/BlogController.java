package com.example.blog.Controller;

import com.example.blog.ControllerAdvise.AllException;
import com.example.blog.Model.BlogModel;
import com.example.blog.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody BlogModel blog){
            blogService.add(blog);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody BlogModel blog){
        blogService.update(id, blog);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        blogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    @GetMapping("/getBlogById/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.findBlogModelById(id));
    }

    @GetMapping("/getBlogByTitle/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){

        return ResponseEntity.status(HttpStatus.OK).body(blogService.findBlogModelByTitle(title));
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.findBlogModelByCategory(category));
    }

    @PutMapping("/update/status")
    public ResponseEntity updateStatus(){
        blogService.findAllByIs_Published();
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
