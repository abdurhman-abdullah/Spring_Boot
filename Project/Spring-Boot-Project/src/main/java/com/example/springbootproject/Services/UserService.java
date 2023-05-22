package com.example.springbootproject.Services;

import com.example.springbootproject.Model.Merchant;
import com.example.springbootproject.Model.MerchantStock;
import com.example.springbootproject.Model.Product;
import com.example.springbootproject.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
@Service
public class UserService {
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantService merchantService;

    public UserService(MerchantStockService merchantStockService, ProductService productService, MerchantService merchantService) {
        this.merchantStockService = merchantStockService;
        this.productService = productService;
        this.merchantService = merchantService;
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

    public String checks(int userId, int productId, int merchantId){
        Product product = null;
        MerchantStock merchantStock = null;
        Merchant merchant = null;
        User user = null;

        if(checkId(userId) != null &&
                (this.productService.checkId(productId) != null && this.merchantService.checkId(merchantId) != null)){
            for (int i = 0; i < this.merchantStockService.getAll().size(); i++) {
                if (Integer.parseInt(this.merchantStockService.getAll().get(i).getProductId()) == productId &&
                        Integer.parseInt(this.merchantStockService.getAll().get(i).getMerchantId()) == merchantId) {
                    merchantStock = this.merchantStockService.getAll().get(i);
                    if (merchantStock != null) {
                        product = this.productService.get(productId);
                        merchant = this.merchantService.get(merchantId);
                    }
                }
            }
            }else {

            return "BadRequest";
        }
        if(get(userId) != null) {
            user = get(userId);
            int balance = Integer.parseInt(user.getBalance());
            int prise = Integer.parseInt(product.getPrice());

            if (prise > balance) {
                return "BadRequest";
            }


            int newPrise = balance - prise;
            user.setBalance(String.valueOf(newPrise));
            update(Integer.parseInt(user.getId()), user);
            this.merchantStockService.delete(Integer.parseInt(merchantStock.getId()));
        }

        return "Success";
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

    public User get(int id){
        if(checkId(id) != null)
        for(int i = 0; i < users.size(); i++){
            return users.get(i);
        }

        return null;
    }
}
