package com.example.springboot8.Controllers;

import com.example.springboot8.Models.Book;
import com.example.springboot8.Services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(bookService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Book book){
        this.bookService.add(book);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Book book){
        bookService.update(id, book);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity update(@PathVariable int id){
        bookService.delete(id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getAllBookByAuthor/{author}")
    public ResponseEntity booksByAuthor(@PathVariable String author){
        bookService.booksByAuthor(author);
        return ResponseEntity.status(200).body(bookService.booksByAuthor(author));
    }

    @GetMapping("/getAllBookByCategory/{category}")
    public ResponseEntity booksByCategory(@PathVariable String category){
        return ResponseEntity.status(200).body(bookService.booksByCategory(category));
    }

    @GetMapping("/getAllBooksByTitle/{title}")
    public ResponseEntity booksByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(bookService.booksByTitle(title));
    }

    @GetMapping("/getAllBooksByNumberOfPages")
    public ResponseEntity booksByNumberOfPages(){
        return ResponseEntity.status(200).body(bookService.booksByNumberOfPages());
    }
}
