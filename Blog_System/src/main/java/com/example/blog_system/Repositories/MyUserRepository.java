package com.example.blog_system.Repositories;

import com.example.blog_system.Models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserByUsername(String username);
}
