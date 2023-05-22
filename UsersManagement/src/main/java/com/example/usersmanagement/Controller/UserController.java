package com.example.usersmanagement.Controller;

import com.example.usersmanagement.Model.users;
import com.example.usersmanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody users user, Errors error){
        if(userService.checkErrors(error) != null){
            return ResponseEntity.status(400).body(userService.getAllErrors(error));
        }

        if(user.getROLE().equals("admin") || user.getROLE().equals("user")){
            userService.add(user);
            return ResponseEntity.status(200).body("Success");
        }
        return ResponseEntity.status(400).body("role have to be in admin or user");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody users user, Errors error){
        if(userService.checkErrors(error) != null)
            return ResponseEntity.status(400).body(userService.getAllErrors(error));

        if(!userService.update(id, user))
            return ResponseEntity.status(400).body("not found id");

        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        if(!userService.delete(id))
            return ResponseEntity.status(200).body("not found id");

        return ResponseEntity.status(200).body("Success");
    }

}
