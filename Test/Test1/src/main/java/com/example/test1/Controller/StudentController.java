package com.example.test1.Controller;

import com.example.test1.Model.Student;
import com.example.test1.Serveice.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(this.studentService.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity get(@RequestBody Student name){
        if(this.studentService.get(name).getName() != name.getName()) {
            return ResponseEntity.status(400).body("Not found name");
        }
            return ResponseEntity.status(200).body(this.studentService.get(name));

    }


    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Student student, Errors errors){
        if(this.studentService.getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(this.studentService.getAllErrors(errors));
        }

        if(this.studentService.checkId(Integer.parseInt(student.getId()))){
            return ResponseEntity.status(400).body("this id is exist");
        }
        this.studentService.add(student);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update")
    public ResponseEntity update(@PathVariable int id, @RequestBody Student student, Errors errors){
        if(this.studentService.getAllErrors(errors) != null)
            return ResponseEntity.status(400).body(this.studentService.getAllErrors(errors));

        if(!this.studentService.update(id, student))
            return ResponseEntity.status(400).body("id Not found");

        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable int id){
        if(!this.studentService.delete(id))
            return ResponseEntity.status(400).body("id Not found");

        return ResponseEntity.status(200).body(this.studentService.delete(id));
    }
}
