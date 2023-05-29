package com.example.homework4.Controllers;

import com.example.homework4.Models.Course;
import com.example.homework4.Models.Teacher;
import com.example.homework4.Services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        List<Course> courseList = courseService.getAll();
        return ResponseEntity.status(200).body(courseList);
    }


    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Course course, Errors errors){
        courseService.add(course, errors);
        return ResponseEntity.status(200).body("course added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody Course course, Errors errors){
        courseService.update(id, course, errors);
        return ResponseEntity.status(200).body("course Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        courseService.delete(id);
        return ResponseEntity.status(200).body("course deleted");
    }

    @PutMapping("/teacher/{teacher_id}/course/{course_id}")
    public ResponseEntity assignCourseToTeacher(@PathVariable int teacher_id, @PathVariable int course_id){
        courseService.assignCourseToTeacher(course_id, teacher_id);
        return ResponseEntity.status(200).body("assign course to teacher success");
    }
}
