package com.example.springboot8.Repositories;

import com.example.springboot8.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByCategory(String category);

    @Query("select b from Book b where b.numberofpages >= 300")
    List<Book> findBooksByNumberOfPages();

    List<Book> findBooksByAuthor(String author);

    @Query("select b from Book b where b.title = ?1")
    Book findBooksByTitle(String title);

}
