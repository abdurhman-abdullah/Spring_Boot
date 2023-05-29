package com.example.homework4.Services;

import com.example.homework4.Excptions.ExceptionApiResponse;
import com.example.homework4.Models.Course;
import com.example.homework4.Models.Teacher;
import com.example.homework4.Repositories.CourseRepository;
import com.example.homework4.Repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public void add(Course course, Errors errors){
        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        courseRepository.save(course);
    }

    public void update(int id, Course course, Errors errors){
        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        Course findCourse = courseRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));

        findCourse.setName(course.getName());
        courseRepository.save(findCourse);
    }

    public void delete(int id){
        Course findCourse = courseRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));
        courseRepository.delete(findCourse);
    }
    public void assignCourseToTeacher(int course_id, int teacher_id){
        Course findCourse = courseRepository.findById(course_id).
                orElseThrow(() -> new ExceptionApiResponse("can't assign course, id is not found"));

        Teacher findTeacher = teacherRepository.findById(teacher_id).
                orElseThrow(() -> new ExceptionApiResponse("can't assign teacher, id is not found"));

        findCourse.setTeacher(findTeacher);

        courseRepository.save(findCourse);
    }
}
