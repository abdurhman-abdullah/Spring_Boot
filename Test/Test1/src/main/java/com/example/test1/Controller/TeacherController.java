package com.example.test1.Controller;

import com.example.test1.Model.Student;
import com.example.test1.Model.Teacher;
import com.example.test1.Serveice.StudentService;
import com.example.test1.Serveice.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(this.teacherService.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity get(@RequestBody Teacher teacher){
        if(this.teacherService.get(teacher).getId() == teacher.getId()) {
            return ResponseEntity.status(400).body("Not found name");
        }
        return ResponseEntity.status(200).body(this.teacherService.get(teacher));

    }


    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Teacher teacher, Errors errors){
        if(this.teacherService.getAllErrors(errors) != null){
            return ResponseEntity.status(400).body(this.teacherService.getAllErrors(errors));
        }

        if(this.teacherService.checkId(Integer.parseInt(teacher.getId()))){
            return ResponseEntity.status(400).body("this id is exist");
        }
        this.teacherService.add(teacher);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Teacher teacher, Errors errors){
        if(this.teacherService.getAllErrors(errors) != null)
            return ResponseEntity.status(400).body(this.teacherService.getAllErrors(errors));

        if(!this.teacherService.update(id, teacher))
            return ResponseEntity.status(400).body("id Not found");

        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(!this.teacherService.delete(id))
            return ResponseEntity.status(400).body("id Not found");

        return ResponseEntity.status(200).body("success");
    }
}
