package com.example.springbootproject.Controller;

import com.example.springbootproject.ApiMessage.ApiMessage;
import com.example.springbootproject.Model.Merchant;
import com.example.springbootproject.Services.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        ArrayList<Merchant> merchants = this.merchantService.getAll();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Merchant merchant, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(this.merchantService.checkId(Integer.parseInt(merchant.getId())) != null){
            return ResponseEntity.status(400).body(new ApiMessage(this.merchantService.checkId(Integer.parseInt(merchant.getId()))));
        }

        this.merchantService.add(merchant);
        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Merchant merchant, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(!this.merchantService.update(id, merchant)){
            return ResponseEntity.status(400).body(new ApiMessage("this id is not Exist"));
        }

        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if( this.merchantService.delete(id)) {
            return ResponseEntity.status(200).body(new ApiMessage("Success"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
    }

    public ArrayList<String> getAllErrors(Errors errors){
        ArrayList<String> getAllErrors = this.merchantService.checkErrors(errors);
        return getAllErrors;
    }
}
