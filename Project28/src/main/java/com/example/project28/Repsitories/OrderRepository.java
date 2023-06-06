package com.example.project28.Repsitories;

import com.example.project28.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomer_Id(Integer userId);

    Order findOrderByIdAndCustomer_Id(Integer userId, Integer orderId);
}
