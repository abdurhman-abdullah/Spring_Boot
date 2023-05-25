package com.example.springboot8.Repositories;

import com.example.springboot8.Models.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    Librarian getById(int id);

    @Query("select l from Librarian l where l.username = ?1 and l.password = ?2")
    Librarian getByUsernameAndPassword(String userName, String password);

    Librarian getByEmail(String email);

}
