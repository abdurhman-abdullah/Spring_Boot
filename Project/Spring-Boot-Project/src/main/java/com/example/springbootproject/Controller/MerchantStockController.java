package com.example.springbootproject.Controller;

import com.example.springbootproject.ApiMessage.ApiMessage;
import com.example.springbootproject.Model.MerchantStock;
import com.example.springbootproject.Services.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        ArrayList<MerchantStock> merchantStocks = this.merchantStockService.getAll();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(this.merchantStockService.checkId(Integer.parseInt(merchantStock.getId())) != null){
            return ResponseEntity.status(400).body(new ApiMessage(this.merchantStockService.checkId(Integer.parseInt(merchantStock.getId()))));
        }

        if(!this.merchantStockService.getProduct(merchantStock)){
            return ResponseEntity.status(400).body(new ApiMessage("This productId not found"));
        }

        if(!this.merchantStockService.getMerchant(merchantStock)){
            return ResponseEntity.status(400).body(new ApiMessage("This merchantId not found"));
        }

        this.merchantStockService.add(merchantStock);
        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(!this.merchantStockService.update(id, merchantStock)){
            return ResponseEntity.status(400).body(new ApiMessage("this id is not Exist"));
        }

        if(!this.merchantStockService.getProduct(merchantStock)){
            return ResponseEntity.status(400).body(new ApiMessage("This productId not found"));
        }

        if(!this.merchantStockService.getMerchant(merchantStock)){
            return ResponseEntity.status(400).body(new ApiMessage("This merchantId not found"));
        }

        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if( this.merchantStockService.delete(id)) {
            return ResponseEntity.status(200).body(new ApiMessage("Success"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
    }

    public ArrayList<String> getAllErrors(Errors errors){
        ArrayList<String> getAllErrors = this.merchantStockService.checkErrors(errors);
        return getAllErrors;
    }
}
