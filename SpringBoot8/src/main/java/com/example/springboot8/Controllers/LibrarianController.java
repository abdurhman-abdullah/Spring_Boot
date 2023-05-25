package com.example.springboot8.Controllers;

import com.example.springboot8.Models.Book;
import com.example.springboot8.Models.Librarian;
import com.example.springboot8.Services.LibrarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/librarian")
@RequiredArgsConstructor
public class LibrarianController {
    private final LibrarianService librarianService;
    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(librarianService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Librarian librarian){
        this.librarianService.add(librarian);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id,@Valid @RequestBody Librarian librarian){
        librarianService.update(id, librarian);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        librarianService.delete(id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable int id){
        return ResponseEntity.status(200).body(librarianService.getById(id));
    }

    @GetMapping("/getByUserNameAndPassword/{userName}/{password}")
    public ResponseEntity getByUserNameAndPassword(@PathVariable String userName, @PathVariable String password){
        return ResponseEntity.status(200).body(librarianService.getByUsernameAndPassword(userName, password));
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(librarianService.getByEmail(email));
    }

}
