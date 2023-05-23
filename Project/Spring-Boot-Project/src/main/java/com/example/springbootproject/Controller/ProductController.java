package com.example.springbootproject.Controller;

import com.example.springbootproject.ApiMessage.ApiMessage;
import com.example.springbootproject.Model.Product;
import com.example.springbootproject.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        ArrayList<Product> products = this.productService.getAll();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Product product, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(this.productService.checkId(Integer.parseInt(product.getId())) != null){
            return ResponseEntity.status(400).body(new ApiMessage(this.productService.checkId(Integer.parseInt(product.getId()))));
        }

        if(!this.productService.getCategory(product)){
            return ResponseEntity.status(400).body(new ApiMessage("This categoryID not found"));
        }

        this.productService.add(product);
        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Product product, Errors errors){

        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(!this.productService.getCategory(product)){
            return ResponseEntity.status(400).body(new ApiMessage("This categoryID not found"));
        }

        if(!productService.update(id, product)) {
            return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
        }
        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(productService.delete(id)) {
            return ResponseEntity.status(200).body(new ApiMessage("Success"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
    }

    public ArrayList<String> getAllErrors(Errors errors){
        ArrayList<String> getAllErrors = this.productService.checkErrors(errors);
        return getAllErrors;
    }


}
