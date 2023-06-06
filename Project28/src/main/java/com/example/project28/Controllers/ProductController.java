package com.example.project28.Controllers;

import com.example.project28.Models.Customer;
import com.example.project28.Models.Order;
import com.example.project28.Models.Product;
import com.example.project28.Services.OrderService;
import com.example.project28.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(this.productService.getAllProduct());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Product product){
        this.productService.add(product);
        return ResponseEntity.status(200).body("product added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Product product){
        this.productService.update(id, product);
        return ResponseEntity.status(200).body("product updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        this.productService.delete(id);
        return ResponseEntity.status(200).body("product deleted");
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity getProductById(@PathVariable int id){
        return ResponseEntity.status(200).body(this.productService.getProduct(id));
    }

}
