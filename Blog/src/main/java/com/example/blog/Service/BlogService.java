package com.example.blog.Service;

import com.example.blog.ApiException.ApiException;
import com.example.blog.ControllerAdvise.AllException;
import com.example.blog.Model.BlogModel;
import com.example.blog.Repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AllException allException;

    public List<BlogModel> getAll(){
        return blogRepository.findAll();
    }

    public BlogModel findBlogModelById(Integer id){
        BlogModel blog = blogRepository.findBlogModelById(id);
        return blog;
    }

    public BlogModel findBlogModelByTitle(String title){
        BlogModel blog = blogRepository.findBlogModelByTitle(title);
        return blog;
    }

    public BlogModel findBlogModelByCategory(String category){
        BlogModel blog = blogRepository.findBlogModelByCategory(category);
        return blog;
    }

    public void add(BlogModel blog){
        if(blog.getCategory().equals("health") || blog.getCategory().equals("education") || blog.getCategory().equals("programming"))
            blogRepository.save(blog);
    }

    public void update(Integer id, BlogModel blog){
        BlogModel blogById = blogRepository.getById(id);

        blogById.setTitle(blog.getTitle());
        blogById.setCategory(blog.getCategory());
        blogById.setBody(blog.getBody());
        blogRepository.save(blogById);
    }
    public void findAllByIs_Published(){
        List<BlogModel> blogs = blogRepository.findAllByIs_Published();
        for(int i = 0; i < blogs.size(); i++){
            blogs.get(i).setIs_Published(1);
            blogs.set(i, blogs.get(i));
        }
    }

    public void delete(Integer id){
        BlogModel blog = blogRepository.getById(id);
        blogRepository.delete(blog);
    }
}
