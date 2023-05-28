package com.example.homework4.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(generator = "id_teacher_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_teacher_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String name;

    @NotNull(message = "age must be not null")
    @Column(columnDefinition = "int")
    private Integer age;

    @NotEmpty(message = "email must be not empty")
    @Email(message = "email must be valid empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String email;

    @NotNull(message = "salary must be not null")
    @Column(columnDefinition = "int")
    private Integer salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;
}
