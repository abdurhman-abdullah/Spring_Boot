package com.example.project4.Controllers;

import com.example.project4.Models.Movie;
import com.example.project4.Services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(movieService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Movie movie, Errors errors){
        movieService.add(movie, errors);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Movie movie){
        movieService.update(id, movie);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        movieService.delete(id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity getByName(@PathVariable String name){
        return ResponseEntity.status(200).body(movieService.getByName(name));
    }

    @GetMapping("/getByName/duration/{name}")
    public ResponseEntity getByName_Duration(@PathVariable String name){
        return ResponseEntity.status(200).body(movieService.getByName_Duration(name));
    }

    @GetMapping("/getByName/rating/{name}")
    public ResponseEntity getByName_Rating(@PathVariable String name){
        return ResponseEntity.status(200).body(movieService.getByName_Rating(name));
    }

    @GetMapping("/getAllByRate/{rate}")
    public ResponseEntity getAllByRate(@PathVariable float rate){
        return ResponseEntity.status(200).body(movieService.getAllByRate(rate));
    }

    @GetMapping("/getAllByGenre/{genre}")
    public ResponseEntity getAllByGenre(@PathVariable String genre){
        return ResponseEntity.status(200).body(movieService.getAllByGenre(genre));
    }

    @GetMapping("/getDirector/{name}")
    public ResponseEntity getDirectorName(@PathVariable String name){
        return ResponseEntity.status(200).body(movieService.getByName_Director(name));
    }

    @GetMapping("/getAllMovie/{id}")
    public ResponseEntity getAllMovieByDirectorName(@PathVariable int id){
        return ResponseEntity.status(200).body(movieService.getAllMovieByDirector(id));
    }
}
