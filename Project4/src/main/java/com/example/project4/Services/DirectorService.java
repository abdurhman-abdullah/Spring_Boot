package com.example.project4.Services;

import com.example.project4.Exception.RecordNotFoundException;
import com.example.project4.Models.Director;
import com.example.project4.Models.Movie;
import com.example.project4.Repositories.DirectorRepository;
import com.example.project4.Repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final MovieService movieService;
    public List<Director> getAll(){
        return directorRepository.findAll();
    }

    public void add(Director director){
        directorRepository.save(director);
    }

    public void update(int id, Director director){
        Director findDirector = directorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("id not found : " + id));

        findDirector.setName(director.getName());
        directorRepository.save(findDirector);
    }

    public void delete(int id){
        directorRepository.delete(findByMovieId(id));
    }

    public Director findByMovieId(int directorId){
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new RecordNotFoundException("id not found : " + directorId));

        List<Movie> movies = movieService.getAllMovieByDirector(director.getId());

        for(int i = 0; i < movies.size(); i++){
           int movieId = movies.get(i).getId();
            movieService.delete(movieId);
        }
        return director;
    }


}
