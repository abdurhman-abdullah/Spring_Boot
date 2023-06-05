package com.example.blog_system.Services;

import com.example.blog_system.Exceptions.ApiException;
import com.example.blog_system.Models.Blog;
import com.example.blog_system.Models.MyUser;
import com.example.blog_system.Repositories.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public List<Blog> getAllBlogs(Integer userId){
        return blogRepository.findBlogsByMyUser_Id(userId);
    }

    public void add(MyUser userId, Blog blog){
        blog.setMyUser(userId);
        blogRepository.save(blog);
    }

    public void update(int id, int userId, Blog blog){
        Blog findBlog = blogRepository.findById(id).orElseThrow(() -> new ApiException("blog is not found"));

        if(!findBlog.getMyUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        findBlog.setTitle(blog.getTitle());
        findBlog.setBody(blog.getBody());
        blogRepository.save(findBlog);
    }

    public void delete(int id, int userId){
        Blog findBlog = blogRepository.findById(id).orElseThrow(() -> new ApiException("blog is not found"));

        if(!findBlog.getMyUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        blogRepository.delete(findBlog);
    }

    public Blog getBlogById(Integer userId, int blogId){
        Blog findBlog = blogRepository.findById(blogId).orElseThrow(() -> new ApiException("blog is not found"));

        if(!findBlog.getMyUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        return findBlog;
    }

    public Blog getBlogByTitle(Integer userId, String blogTitle){
        Blog findBlog = blogRepository.findBlogByTitle(blogTitle);

        if(findBlog == null)
            throw new ApiException("title not found");

        if(!findBlog.getMyUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        return findBlog;
    }
}
