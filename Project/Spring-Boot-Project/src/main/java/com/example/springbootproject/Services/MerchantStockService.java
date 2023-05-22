package com.example.springbootproject.Services;

import com.example.springbootproject.Model.Merchant;
import com.example.springbootproject.Model.MerchantStock;
import com.example.springbootproject.Model.Product;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
@Service
public class MerchantStockService {
    private final ProductService productService;
    private final MerchantService merchantService;

    public MerchantStockService(ProductService productService, MerchantService merchantService) {
        this.productService = productService;
        this.merchantService = merchantService;
    }

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<MerchantStock> getAll(){
        return merchantStocks;
    }

    public void add(MerchantStock merchantStock){
        if(getProduct(merchantStock) != null && getMerchant(merchantStock) != null)
        merchantStocks.add(merchantStock);
    }

    public boolean update(int id, MerchantStock merchantStock){
        for(int i = 0; i < merchantStocks.size(); i++){
            if(Integer.parseInt(merchantStocks.get(i).getId()) == id){
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }
    public boolean delete(int id){
        for(int i = 0; i < merchantStocks.size(); i++){
            if(Integer.parseInt(merchantStocks.get(i).getId()) == id){
                merchantStocks.remove(i);
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
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (Integer.parseInt(merchantStocks.get(i).getId()) == id)
                return "This id is exist";
        }
        return null;
    }

    public Product getProduct(MerchantStock merchantStock){
        products = this.productService.getAll();
        for (int i = 0; i < products.size(); i++){
            if(merchantStock.getProductId().equals(products.get(i).getId()))
                return products.get(i);
        }
        return null;
    }

    public Merchant getMerchant(MerchantStock merchantStock){
        merchants = this.merchantService.getAll();
        for (int i = 0; i < merchants.size(); i++){
            if(merchantStock.getMerchantId().equals(merchants.get(i).getId()))
                return merchants.get(i);
        }
        return null;
    }


}
