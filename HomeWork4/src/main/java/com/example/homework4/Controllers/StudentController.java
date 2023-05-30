package com.example.homework4.Controllers;

import com.example.homework4.Models.Student;
import com.example.homework4.Services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        List<Student> courseList = studentService.getAll();
        return ResponseEntity.status(200).body(courseList);
    }


    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Student student, Errors errors){
        studentService.add(student, errors);
        return ResponseEntity.status(200).body("student added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody Student student, Errors errors){
        studentService.update(id, student, errors);
        return ResponseEntity.status(200).body("student Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        studentService.delete(id);
        return ResponseEntity.status(200).body("student deleted");
    }

    @PutMapping("/updateMajor/{id}/{major}")
    public ResponseEntity updateMajor(@PathVariable Integer id, @PathVariable String major){
        studentService.updateMajor(id, major);
        return ResponseEntity.status(200).body("major Updated");
    }

    @GetMapping("/getAllByCourse/{id}")
    public ResponseEntity getAllByCourse(@PathVariable Integer id){
        return ResponseEntity.status(200).body(studentService.getAllByCourse(id));
    }

    @PutMapping("/student/{student_id}/course/{course_id}")
    public ResponseEntity assignStudentToCourse(@PathVariable int student_id, @PathVariable int course_id){
        studentService.assignStudentToCourse(student_id, course_id);
        return ResponseEntity.status(200).body("assign course to teacher success");
    }
}
