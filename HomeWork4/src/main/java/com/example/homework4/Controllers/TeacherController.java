package com.example.homework4.Controllers;

import com.example.homework4.Models.Teacher;
import com.example.homework4.Services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        List<Teacher> customerList= teacherService.getAll();
        return ResponseEntity.status(200).body(customerList);
    }


    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Teacher teacher, Errors errors){
        teacherService.add(teacher, errors);
        return ResponseEntity.status(200).body("teacher added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody Teacher teacher, Errors errors){
        teacherService.update(id, teacher, errors);
        return ResponseEntity.status(200).body("teacher Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        teacherService.delete(id);
        return ResponseEntity.status(200).body("teacher deleted");

    }
}
