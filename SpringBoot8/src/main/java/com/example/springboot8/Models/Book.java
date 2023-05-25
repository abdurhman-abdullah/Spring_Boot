package com.example.springboot8.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(generator = "id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "title not empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String title;

    @NotEmpty(message = "author not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String author;

    @NotEmpty(message = "category not empty")
    @Column(columnDefinition = "varchar(50) not null check(category = 'Academic' or category = 'Mystery' or category = 'Novel')")
    private String category;

    @NotNull(message = "ISBN not empty")
    @Column(columnDefinition = "int unique")
    private Integer ispn;

    @NotNull(message = "numberOfPages not empty")
    @Min(50)
    @Column(columnDefinition = "int")
    private Integer numberofpages;
}
