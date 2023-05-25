package com.example.springboot8.Services;

import com.example.springboot8.Exceptions.ApiException;
import com.example.springboot8.Exceptions.ExceptionMessage;
import com.example.springboot8.Models.Book;
import com.example.springboot8.Repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public void add(Book book){
         bookRepository.save(book);
    }

    public void update(int id, Book book){
        Book findBook = bookRepository.getById(id);

        if(findBook.equals(null))
            throw new ApiException("id is not found");

        findBook.setAuthor(book.getAuthor());
        findBook.setCategory(book.getCategory());
        findBook.setTitle(book.getTitle());
        findBook.setIspn(book.getIspn());
        findBook.setNumberofpages(book.getNumberofpages());
        bookRepository.save(findBook);
    }

    public void delete(int id){
       Book book = bookRepository.getById(id);

       if(book.equals(null))
           throw new ApiException("category not found");

        bookRepository.delete(book);

    }

    public List<Book> booksByCategory(String category){
        List<Book> books = bookRepository.findBooksByCategory(category);
        if(books.size() == 0)
            throw new ApiException("category not found");

       return bookRepository.findBooksByCategory(category);
    }

    public List<Book> booksByNumberOfPages(){
        return bookRepository.findBooksByNumberOfPages();
    }

    public List<Book> booksByAuthor(String author){
            List<Book> books = bookRepository.findBooksByAuthor(author);
            if(books.size() == 0)
                throw new ApiException("author not found");

        return bookRepository.findBooksByAuthor(author);
    }

    public Book booksByTitle(String title){
        Book book = bookRepository.findBooksByTitle(title.toLowerCase());

        if(book == null)
            throw new ApiException("title not found");

        return bookRepository.findBooksByTitle(title);
    }
}
