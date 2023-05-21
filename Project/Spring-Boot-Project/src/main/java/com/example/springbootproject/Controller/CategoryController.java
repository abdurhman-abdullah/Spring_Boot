package com.example.springbootproject.Controller;

import com.example.springbootproject.ApiMessage.ApiMessage;
import com.example.springbootproject.Model.Category;
import com.example.springbootproject.Services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        ArrayList<Category> categories = this.categoryService.getAll();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Category category, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }
        if(this.categoryService.checkId(Integer.parseInt(category.getId())) != null){
            return ResponseEntity.status(400).body(new ApiMessage(this.categoryService.checkId(Integer.parseInt(category.getId()))));
        }

        this.categoryService.add(category);
        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Category category, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(!this.categoryService.update(id, category)){
            return ResponseEntity.status(400).body(new ApiMessage("this id is not Exist"));
        }

        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if( this.categoryService.delete(id)) {
            this.categoryService.delete(id);
            return ResponseEntity.status(200).body(new ApiMessage("Success"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
    }

    public ArrayList<String> getAllErrors(Errors errors){
        ArrayList<String> getAllErrors = this.categoryService.checkErrors(errors);
        return getAllErrors;
    }


}
