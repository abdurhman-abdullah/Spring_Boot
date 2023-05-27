package com.example.project4.Services;

import com.example.project4.Exception.RecordNotFoundException;
import com.example.project4.Models.Director;
import com.example.project4.Models.Movie;
import com.example.project4.Repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import java.util.List;
@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public void add(Movie movie, Errors errors){
        if(errors.hasErrors())
            throw new RecordNotFoundException(errors.getFieldError().getDefaultMessage());

       String genre = StringUtils.capitalize(movie.getGenre().toLowerCase());

        if(!genre.equals("Drama") &&
                (!genre.equals("Action") && !genre.equals("Comedy")))
            throw new RecordNotFoundException("The genre should be action, drama or comedy");

        findByDirectorId(movie.getDirectorId());

        movie.setName(StringUtils.capitalize(movie.getName().toLowerCase()));
        movie.setGenre(genre);
        movieRepository.save(movie);
    }

    public void update(int id, Movie movie){
        Movie findMovie = movieRepository.findById(id).
                orElseThrow(() -> new RecordNotFoundException("id not found : " + id));

       findByDirectorId(movie.getDirectorId());

        findMovie.setName(StringUtils.capitalize(movie.getName().toLowerCase()));
        findMovie.setGenre(StringUtils.capitalize(movie.getGenre().toLowerCase()));
        findMovie.setRating(movie.getRating());
        findMovie.setDuration(movie.getDuration());
        findMovie.setDirectorId(movie.getDirectorId());

        movieRepository.save(findMovie);
    }

    public void delete(int id){
        Movie findMovie = movieRepository.findById(id).
                orElseThrow(() -> new RecordNotFoundException("id not found : " + id));

        movieRepository.delete(findMovie);
    }

    public Movie getByName(String name){
        Movie findMovie = movieRepository.findByName(StringUtils.capitalize(name.toLowerCase()));
        if(findMovie == null)
            throw new RecordNotFoundException("this " + name + " is not found");
        return findMovie;
    }

    public String getByName_Duration(String name){
        Movie findMovie = movieRepository.findByName(StringUtils.capitalize(name.toLowerCase()));
        if(findMovie == null)
            throw new RecordNotFoundException("this " + name + " is not found");
        return "Duration: " + findMovie.getDuration() + " minutes";
    }
    public String getByName_Rating(String name){
        Movie findMovie = movieRepository.findByName(StringUtils.capitalize(name.toLowerCase()));
        if(findMovie == null)
            throw new RecordNotFoundException("this " + name + " is not found");
        return "Rating: " + findMovie.getRating() + "/5";
    }

    public String getByName_Director(String name){
        Movie findMovie = movieRepository.findByName(StringUtils.capitalize(name.toLowerCase()));
        if(findMovie == null)
            throw new RecordNotFoundException("this " + name + " is not found");

        Director director = findByDirectorId(findMovie.getDirectorId());

        return "DirectorName: " + director.getName();
    }
    public List<Movie> getAllMovieByDirector(int id){
        Director findDirector = findByDirectorId(id);

        List<Movie> movies = movieRepository.findAllByDirectorId(findDirector.getId());
        if(movies.isEmpty())
            throw new RecordNotFoundException("This id does not have a movie");

        return movies;
    }


    public List<Movie> getAllByRate(float rate){
        List<Movie> findMovie = movieRepository.findAllByRating(rate);

        if(findMovie.isEmpty())
            throw new RecordNotFoundException("This rate does not have a movie");

        return findMovie;
    }

    public List<Movie> getAllByGenre(String genre){
        List<Movie> findMovie = movieRepository.findAllByGenre(StringUtils.capitalize(genre.toLowerCase()));

        if(findMovie.isEmpty())
            throw new RecordNotFoundException("This genre does not have a movie");

        return findMovie;
    }

    public Director findByDirectorId(int directorId){
        Director director = movieRepository.findByDirectorId(directorId);
        if(director == null)
            throw new RecordNotFoundException("this id is not found");
        return director;
    }


}
