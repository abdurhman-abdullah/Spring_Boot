package com.example.homework4.Services;

import com.example.homework4.Excptions.ExceptionApiResponse;
import com.example.homework4.Models.Course;
import com.example.homework4.Models.Student;
import com.example.homework4.Models.Teacher;
import com.example.homework4.Repositories.CourseRepository;
import com.example.homework4.Repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public void add(Student student, Errors errors){
        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        studentRepository.save(student);
    }

    public void update(int id, Student student, Errors errors){
        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        Student findStudent = studentRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));

        findStudent.setName(student.getName());
        findStudent.setAge(student.getAge());
        findStudent.setMajor(student.getMajor());
        studentRepository.save(findStudent);
    }

    public void delete(int id){
        Student findStudent = studentRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));
        studentRepository.delete(findStudent);
    }

    public void updateMajor(int id, String major){
        Student findStudent = studentRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));
        Course findCourseByStudent = courseRepository.getCourseByStudents(findStudent);
        if(findCourseByStudent == null)
            throw new ExceptionApiResponse("this student not have course");
        Course findCourse = courseRepository.findByName(major);
        findStudent.setCourses((Set<Course>) null);
        findCourseByStudent.setStudents((Set<Student>) null);
        findStudent.setMajor(major);
        studentRepository.save(findStudent);
        courseRepository.save(findCourse);
    }

    public Set<Student> getAllByCourse(int course_id){
        Course findCourseByStudent = courseRepository.findById(course_id)
                        .orElseThrow(() -> new ExceptionApiResponse("id not found"));
        Set<Student> students = findCourseByStudent.getStudents();
        return students;
    }

    public void assignStudentToCourse(int student_id, int course_id){
        Student findStudent = studentRepository.findById(student_id).
                orElseThrow(() -> new ExceptionApiResponse("can't assign student, id is not found"));

        Course findCourse = courseRepository.findById(course_id).
                orElseThrow(() -> new ExceptionApiResponse("can't assign course, id is not found"));

        findStudent.getCourses().add(findCourse);
        findCourse.getStudents().add(findStudent);

        studentRepository.save(findStudent);
        courseRepository.save(findCourse);
    }
}
