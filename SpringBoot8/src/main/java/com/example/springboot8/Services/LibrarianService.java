package com.example.springboot8.Services;

import com.example.springboot8.Exceptions.ApiException;
import com.example.springboot8.Models.Book;
import com.example.springboot8.Models.Librarian;
import com.example.springboot8.Repositories.LibrarianRepository;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LibrarianService {

    private final LibrarianRepository librarianRepository;

    public List<Librarian> getAll(){
        return librarianRepository.findAll();
    }

    public void add(Librarian librarian){
        librarianRepository.save(librarian);
    }

    public void update(int id, Librarian librarian){
        Librarian findLibrarian = librarianRepository.getById(id);

        if(findLibrarian.equals(null))
            throw new ApiException("this id not found");

        findLibrarian.setName(librarian.getName());
        findLibrarian.setUsername(librarian.getUsername());
        findLibrarian.setPassword(librarian.getPassword());
        librarianRepository.save(findLibrarian);
    }

    public void delete(int id){
        Librarian findLibrarian = librarianRepository.getById(id);

        if(findLibrarian.equals(null))
            throw new ApiException("this id not found");

        librarianRepository.delete(findLibrarian);

    }

    public Librarian getById(int id){
        Librarian findLibrarian = librarianRepository.getById(id);

        if(findLibrarian.equals(null))
            throw new ApiException("this id not found");

        return findLibrarian;
    }

    public Librarian getByUsernameAndPassword(String userName, String password){
        Librarian findLibrarian = librarianRepository.getByUsernameAndPassword(userName, password);

        if(findLibrarian == null)
            throw new ApiException("this username or password incorrect");

        return findLibrarian;
    }

    public Librarian getByEmail(String email){
        Librarian findLibrarian = librarianRepository.getByEmail(email.toLowerCase());

        if(findLibrarian == null)
            throw new ApiException("email not found");

        return findLibrarian;
    }



}
