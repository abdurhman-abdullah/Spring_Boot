package com.example.homework4.Services;

import com.example.homework4.Excptions.ExceptionApiResponse;
import com.example.homework4.Models.Teacher;
import com.example.homework4.Repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public void add(Teacher teacher, Errors errors){
        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        teacherRepository.save(teacher);
    }

    public void update(int id, Teacher teacher, Errors errors){
        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        Teacher findTeacher = teacherRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));

        findTeacher.setName(teacher.getName());
        findTeacher.setEmail(teacher.getEmail());
        findTeacher.setAge(teacher.getAge());
        findTeacher.setSalary(teacher.getSalary());
        teacherRepository.save(findTeacher);
    }

    public void delete(int id){
        Teacher findTeacher = teacherRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));
        teacherRepository.delete(findTeacher);
    }

}
