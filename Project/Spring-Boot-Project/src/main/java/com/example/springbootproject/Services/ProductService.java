package com.example.springbootproject.Services;

import com.example.springbootproject.Model.Category;
import com.example.springbootproject.Model.Product;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
@Service
public class ProductService {
    private final CategoryService categoryService;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();

    public ProductService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ArrayList<Product> getAll(){
        return products;
    }

    public void add(Product product){
           if(getCategory(product))
               products.add(product);
    }

    public boolean update(int id, Product product){
        for(int i = 0; i < products.size(); i++){
            if(Integer.parseInt(products.get(i).getId()) == id){
                if(getCategory(product)) {
                    products.set(i, product);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(int id){
        for(int i = 0; i < products.size(); i++){
            if(Integer.parseInt(products.get(i).getId()) == id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean getCategory(Product product){
        categories = categoryService.getAll();
        for (int i = 0; i < categories.size(); i++){
            if(product.getCategoryID().equals(categories.get(i).getId()))
                return true;
        }
        return false;
    }

    public ArrayList<String> checkErrors(Errors errors){
        ArrayList<String> allErrors = new ArrayList<>();
        String field = null;
        if(errors.hasErrors()){
            for(int i = 0; i < errors.getAllErrors().size(); i++) {
                field = errors.getFieldErrors().get(i).getField().concat(" : " + errors.getAllErrors().get(i).getDefaultMessage());
                allErrors.add(field);
            }
            return allErrors;
        }
        return null;
    }

    public String checkId(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (Integer.parseInt(products.get(i).getId()) == id)
                return "This id is exist";
        }
        return null;
    }

}
