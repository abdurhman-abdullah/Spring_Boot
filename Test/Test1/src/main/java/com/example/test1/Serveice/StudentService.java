package com.example.test1.Serveice;

import com.example.test1.Model.Student;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Student> getAll(){
        return students;
    }

    public Student get(Student student){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getName().equals(student.getName()))
                return students.get(i);
        }
        return null;
    }

    public void add(Student student){
        students.add(student);
    }

    public Boolean update(int id, Student student){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).equals(id)) {
                students.set(i, student);
                return true;
            }
        }

        return false;
    }

    public Boolean delete(int id){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).equals(id))
                students.remove(i);
                return true;
        }
        return false;
    }

    public ArrayList<String> getAllErrors(Errors errors){
        ArrayList<String> allErrors =  new ArrayList<>();
        if(errors.hasErrors()) {
            for (int i = 0; i < errors.getAllErrors().size(); i++) {
                String error = errors.getFieldErrors().get(i).getField().concat(" : " + errors.getAllErrors().get(i).getDefaultMessage());
                allErrors.add(error);
            }
            return allErrors;
        }
        return null;
    }

    public Boolean checkId(int id){
        for(int i = 0; i < students.size(); i++){
            if(Integer.parseInt(students.get(i).getId()) == id)
                return true;
        }

        return false;
    }
}
