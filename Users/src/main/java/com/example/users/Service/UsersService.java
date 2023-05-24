package com.example.users.Service;

import com.example.users.Model.UserManagement;
import com.example.users.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    public List<UserManagement> getAll(){
        return userRepository.findAll();
    }

    public List<UserManagement> getAllUsersByRole(String role){

        if(role == null) {

        }
        List <UserManagement> users = userRepository.findByRole(role);
        return users;
    }

    public List<UserManagement> getAllUsersByAge(int age){
        List <UserManagement> users = userRepository.findByAge(age);
        return users;
    }

    public Object getByEmail(String email){

        if(email == null) {
            return "BadRequest";
        }
        UserManagement user = userRepository.findByEmail(email);

        if(user.getEmail() == null){
            return "Not Found";
        }

        return user;
    }

    public Object findByUsernameAndPassword(String userName, String password){

        if(userName == null && password == null) {
            return "BadRequest";
        }
        UserManagement user = userRepository.findByUsernameAndPassword(userName, password);

        if(user == null){
           return "Not Found";
        }

        return user;
    }

    public void add(UserManagement user){
        userRepository.save(user);
    }

    public Boolean update(Integer id, UserManagement user1){
        UserManagement user = userRepository.getById(id);

        if(user == null)
            return false;

        user.setAge(user1.getAge());
        user.setPassword(user1.getPassword());
        user.setUsername(user1.getUsername());
        user.setEmail(user1.getEmail());

        userRepository.save(user);
        return true;
    }

    public Boolean delete(Integer id){
        UserManagement user = userRepository.getById(id);

        if(user == null)
            return false;

        userRepository.save(user);
        return true;
    }

    public Boolean checkUserName(String userName){
        UserManagement checkUserName = userRepository.findByUsername(userName);
        if(checkUserName == null)
            return true;

        return false;
    }

    public Boolean checkEmail(String email){
        UserManagement checkEmail = userRepository.findByEmail(email);
        if(checkEmail == null)
            return true;

        return false;
    }

}
