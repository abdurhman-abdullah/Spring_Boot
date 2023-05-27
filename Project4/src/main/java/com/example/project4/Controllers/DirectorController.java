package com.example.project4.Controllers;

import com.example.project4.Models.Director;
import com.example.project4.Services.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(directorService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Director director){
        directorService.add(director);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Director director){
        directorService.update(id, director);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        directorService.delete(id);
        return ResponseEntity.status(200).body("Success");
    }
}
