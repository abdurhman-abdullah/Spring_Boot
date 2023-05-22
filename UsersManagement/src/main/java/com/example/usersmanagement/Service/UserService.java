package com.example.usersmanagement.Service;

import com.example.usersmanagement.Model.users;
import com.example.usersmanagement.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<users> getAll(){
        return  userRepository.findAll();
    }

    public void add(users user){
        userRepository.save(user);
    }

    public Boolean update(Integer id, users user1){
        users user = userRepository.getById(id);

        if(user == null)
            return false;

        user.setAGE(user1.getAGE());
        user.setPASSWORD(user1.getPASSWORD());
        user.setUSERNAME(user1.getUSERNAME());
        user.setEMAIL(user1.getEMAIL());

        userRepository.save(user);
        return true;
    }

    public Boolean delete(Integer id){
        users user = userRepository.getById(id);

        if(user == null)
            return false;

        userRepository.save(user);
        return true;
    }

    public ArrayList<String> checkErrors(Errors errors) {
        ArrayList<String> allErrors = new ArrayList<>();
        String field = null;
        if (errors.hasErrors()) {
            for (int i = 0; i < errors.getAllErrors().size(); i++) {
                field = errors.getFieldErrors().get(i).getField().concat(" : " + errors.getAllErrors().get(i).getDefaultMessage());
                allErrors.add(field);
            }
            return allErrors;
        }
        return null;
    }

    public ArrayList<String> getAllErrors(Errors errors){
        ArrayList<String> getAllErrors = checkErrors(errors);
        return getAllErrors;
    }
}
