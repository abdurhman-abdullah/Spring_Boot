package com.example.homework4.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "id_student_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_student_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String name;

    @NotNull(message = "age must be not null")
    @Column(columnDefinition = "int")
    private Integer age;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(50)")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;


}
