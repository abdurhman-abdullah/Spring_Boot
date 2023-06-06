package com.example.project28.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "id_pro_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_pro_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String name;

    @NotNull(message = "price must be not null")
    @Column(columnDefinition = "FLOAT")
    @Positive
    private Double price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Order> orders;

}
