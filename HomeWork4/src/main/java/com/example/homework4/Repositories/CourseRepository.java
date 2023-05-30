package com.example.homework4.Repositories;

import com.example.homework4.Models.Course;
import com.example.homework4.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course getCourseByStudents(Student student);
    Course findByName(String name);

}
