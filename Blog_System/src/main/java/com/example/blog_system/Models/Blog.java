package com.example.blog_system.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(generator = "id_blg_squ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_blg_squ", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "title is empty")
    @Column(columnDefinition = "varchar(20)")
    private String title;

    @NotEmpty(message = "body is empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String body;

    @ManyToOne
    @JoinColumn(name = "myUser_Id", referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
}
