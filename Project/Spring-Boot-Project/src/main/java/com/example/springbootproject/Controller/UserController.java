package com.example.springbootproject.Controller;

import com.example.springbootproject.ApiMessage.ApiMessage;
import com.example.springbootproject.Model.User;
import com.example.springbootproject.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        ArrayList<User> users = this.userService.getAll();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody User user, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(this.userService.checkId(Integer.parseInt(user.getId())) != null){
            return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
        }

//        if(!user.getRole()("Admin") || !user.getRole().equals("Customer")){
//            return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
//        }

        this.userService.add(user);
        return ResponseEntity.status(200).body(new ApiMessage("Success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody User user, Errors errors){
        if(getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(getAllErrors(errors));
        }

        if(this.userService.update(id, user)) {
            return ResponseEntity.status(200).body(new ApiMessage("Success"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(this.userService.delete(id)) {
            return ResponseEntity.status(200).body(new ApiMessage("Success"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));
    }

    @PutMapping("/update/{userId}/{productId}/{merchantId}")
    public ResponseEntity change(@PathVariable int userId,@PathVariable int productId, @PathVariable int merchantId){
        if(this.userService.checks(userId, productId, merchantId) == "BadRequest")
        return ResponseEntity.status(400).body(new ApiMessage("BadRequest"));

        return ResponseEntity.status(200).body(new ApiMessage("Success"));

    }

    public ArrayList<String> getAllErrors(Errors errors){
        ArrayList<String> getAllErrors = this.userService.checkErrors(errors);
        return getAllErrors;
    }
}
