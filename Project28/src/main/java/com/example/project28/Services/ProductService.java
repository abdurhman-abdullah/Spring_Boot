package com.example.project28.Services;

import com.example.project28.Exceptions.ApiException;
import com.example.project28.Models.Product;
import com.example.project28.Repsitories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void add(Product product){
        productRepository.save(product);
    }

    public void update(int id, Product product){
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("product is not found"));

        findProduct.setName(product.getName());
        productRepository.save(findProduct);
    }

    public void delete(int id){
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("product is not found"));

        productRepository.delete(findProduct);
    }

    public Product getProduct(Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ApiException("product is not found"));

        return product;
    }

}
