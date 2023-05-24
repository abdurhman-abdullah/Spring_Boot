package com.example.users.Errors;

import com.example.users.Model.UserManagement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GetErrors {

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
