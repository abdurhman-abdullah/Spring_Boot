package com.example.springbootproject.Services;

import com.example.springbootproject.Model.Merchant;
import com.example.springbootproject.Model.Product;
import com.example.springbootproject.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
@Service
public class UserService {
    private final MerchantStockService merchantStockService;

    public UserService(MerchantStockService merchantStockService) {
        this.merchantStockService = merchantStockService;
    }

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<User> getAll(){
        return users;
    }

    public void add(User user){
            users.add(user);
    }

    public boolean update(int id, User user){
        for(int i = 0; i < users.size(); i++){
            if(Integer.parseInt(users.get(i).getId()) == id){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id){
        for(int i = 0; i < users.size(); i++){
            if(Integer.parseInt(users.get(i).getId()) == id){
                users.remove(i);
                return true;
            }
        }
        return false;
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


    public String checkId(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (Integer.parseInt(users.get(i).getId()) == id)
                return "This id is exist";
        }
        return null;
    }
}
