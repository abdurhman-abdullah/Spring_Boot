package com.example.project4.Repositories;

import com.example.project4.Models.Director;
import com.example.project4.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select d from Director d where d.id = ?1")
    Director findByDirectorId(int directorId);
    Movie findByName(String name);
    @Query("select m from Movie m where m.rating >= ?1")
    List<Movie> findAllByRating(float rate);
    @Query("select m from Movie m where m.genre = ?1")
    List<Movie> findAllByGenre(String genre);
    List<Movie> findAllByDirectorId(int directorId);


}
