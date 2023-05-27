package com.example.project4.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Director {
    @Id
    @GeneratedValue(generator = "id_director_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_director_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name cannot be null")
    @Length(min = 3, message = "name length more than 2")
    @Column(columnDefinition = "varchar(50) unique")
    private String name;
}
