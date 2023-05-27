package com.example.project4.Repositories;

import com.example.project4.Models.Director;
import com.example.project4.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
