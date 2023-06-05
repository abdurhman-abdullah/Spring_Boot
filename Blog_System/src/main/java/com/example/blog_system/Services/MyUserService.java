package com.example.blog_system.Services;

import com.example.blog_system.DTOs.UserDto;
import com.example.blog_system.Exceptions.ApiException;
import com.example.blog_system.Models.MyUser;
import com.example.blog_system.Repositories.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MyUserService {

    private MyUserRepository userRepository;

    public UserDto getAll(Integer userId){
        MyUser myUser = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        UserDto userDto = new UserDto(myUser.getUsername(), myUser.getBlogs());
        userDto.setUsername(myUser.getUsername());
        userDto.setBlogs(myUser.getBlogs());

        return userDto;
    }

    public void add(MyUser user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
    }

    public void update(int userId, MyUser user){
        MyUser findUser = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        findUser.setPassword(hash);
        userRepository.save(findUser);
    }

    public void delete(MyUser userId){
        MyUser findUser = userRepository.findById(userId.getId())
                .orElseThrow(() -> new ApiException("User not found"));

        userRepository.delete(findUser);
    }
}
