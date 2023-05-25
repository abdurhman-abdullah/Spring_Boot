package com.example.blog.Repository;

import com.example.blog.Model.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, Integer> {

    BlogModel findBlogModelById(int id);

    BlogModel findBlogModelByTitle(String title);

    BlogModel findBlogModelByCategory(String category);
    @Query("select b from BlogModel b where b.is_Published = 0")
    List<BlogModel> findAllByIs_Published();
}
