package com.example.test1.Serveice;

import com.example.test1.Model.Student;
import com.example.test1.Model.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();
    public ArrayList<Teacher> getAll(){
        return teachers;
    }

    public Teacher get(Teacher id){
        for(int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getId().equals(id))
                return teachers.get(i);
        }
        return null;
    }

    public void add(Teacher teacher){
        teachers.add(teacher);
    }

    public Boolean update(int id, Teacher teacher){
        for(int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).equals(id)) {
                teachers.set(i, teacher);
                return true;
            }
        }

        return false;
    }

    public Boolean delete(int id){
        for(int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).equals(id))
                teachers.remove(i);
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
        for(int i = 0; i < teachers.size(); i++){
            if(Integer.parseInt(teachers.get(i).getId()) == id)
                return true;
        }

        return false;
    }
}
