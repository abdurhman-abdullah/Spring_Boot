package com.example.project4.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(generator = "id_movie_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_movie_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name cannot be null")
    @Length(min = 3, message = "name length more than 2")
    @Column(columnDefinition = "varchar(300) unique")
    private String name;

    @NotEmpty(message = "genre cannot be null")
    @Column(columnDefinition = "varchar(300) check(genre = 'Drama' or genre = 'Action' or genre = 'Comedy')")
    private String genre;

    @NotNull(message = "rating cannot be null")
    @DecimalMin("1.0")
    @DecimalMax("5")
    @Column(columnDefinition = "float check(rating >= 1.0 and rating <= 5)")
    private Float rating;

    @NotNull(message = "duration cannot be null")
    @Min(value = 60, message = "duration It must be more than 60")
    @Column(columnDefinition = "int check(duration >= 60)")
    private Integer duration;

    @NotNull(message = "directorId cannot be null")
    @Column(columnDefinition = "int")
    private Integer directorId;
}
