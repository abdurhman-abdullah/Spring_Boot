package com.example.project28.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User_Order")
public class Order {
    @Id
    @GeneratedValue(generator = "id_ord_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_ord_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotNull(message = "quantity must be not null")
    @Column(columnDefinition = "int")
    @Positive
    private Integer quantity;

    @NotNull(message = "totalPrice must be not null")
    @Column(columnDefinition = "float")
    @Positive
    private Double totalPrice;

    @NotNull(message = "dateReceived must be not null")
    @Column(columnDefinition = "date")
    @FutureOrPresent
    private LocalDate dateReceived;

    @NotEmpty(message = "status must have (new or inProgress or completed)")
    @Column(columnDefinition = "varchar(20) check(status = 'new' or status = 'inProgress' or status = 'completed')")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    public Order(Integer quantity, Double totalPrice, LocalDate dateReceived, String status, Customer customer, Product product) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.dateReceived = dateReceived;
        this.status = status;
        this.customer = customer;
        this.product = product;
    }
}
