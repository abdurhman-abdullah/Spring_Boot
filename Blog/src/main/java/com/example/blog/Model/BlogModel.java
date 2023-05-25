package com.example.blog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "Blog")
public class BlogModel {

    @Id
    @GeneratedValue(generator = "id_squ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_squ", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "title is empty")
    @Column(columnDefinition = "varchar(20)")
    private String title;

    @NotEmpty(message = "category is empty")
    @Column(columnDefinition = "varchar(50) not null check(category = 'health' or category = 'education' or category = 'programming')")
    private String category;

    @NotEmpty(message = "body is empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String body;

    @Column(columnDefinition = "Number(1) default 0 not null")
    private int is_Published;
}
