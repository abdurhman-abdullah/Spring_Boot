package com.example.blog_system.Controllers;

import com.example.blog_system.Models.MyUser;
import com.example.blog_system.Services.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/myUser")
@RequiredArgsConstructor
public class UserController {

    private final MyUserService myUserService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(this.myUserService.getAll(user.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity add(@Valid @RequestBody MyUser user){
        this.myUserService.add(user);
        return ResponseEntity.status(200).body("user added");
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser userId, @Valid @RequestBody MyUser user){
        this.myUserService.update(userId.getId(), user);
        return ResponseEntity.status(200).body("user updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser userId){
        this.myUserService.delete(userId);
        return ResponseEntity.status(200).body("user deleted");
    }


}
