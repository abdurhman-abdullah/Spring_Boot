package com.example.blog_system.Repositories;

import com.example.blog_system.Models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findBlogsByMyUser_Id(Integer userId);

    Blog findBlogByTitle(String title);

}
