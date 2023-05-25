package com.example.users.Controller;

import com.example.users.Errors.GetErrors;
import com.example.users.Model.UserManagement;
import com.example.users.Service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersService userService;
    private final GetErrors errors;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/getAllUsersByRole/{role}")
    public ResponseEntity getAllUsersByRole(@PathVariable String role){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersByRole(role));
    }

    @GetMapping("/getAllUsersByAge/{age}")
    public ResponseEntity getAllUsersByAge(@PathVariable int age){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersByAge(age));
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getByEmail(email));
    }

    @GetMapping("/getUser/{userName}/{password}")
    public ResponseEntity getUser(@PathVariable String userName, @PathVariable String password){

        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsernameAndPassword(userName, password));
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody UserManagement user, Errors error){
        if(errors.checkErrors(error) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors(error));
        }

        if(!this.userService.checkUserName(user.getUsername()))
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST);

        if(!this.userService.checkEmail(user.getEmail()))
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email already exists");

        if(user.getRole().equals("admin") || user.getRole().equals("user")){
            userService.add(user);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this role not accept");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody UserManagement user, Errors error){
        if(errors.checkErrors(error) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors(error));

        if(!userService.update(id, user))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);


        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        if(!userService.delete(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id is not found");

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

}
