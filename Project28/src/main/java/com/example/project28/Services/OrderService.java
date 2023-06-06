package com.example.project28.Services;

import com.example.project28.DTOs.OrderInsertDto;
import com.example.project28.DTOs.OrderUpdateDto;
import com.example.project28.Exceptions.ApiException;
import com.example.project28.Models.Customer;
import com.example.project28.Models.Order;
import com.example.project28.Models.Product;
import com.example.project28.Repsitories.OrderRepository;
import com.example.project28.Repsitories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public List<Order> getAllOrder(Integer userId){
        return orderRepository.findAllByCustomer_Id(userId);
    }

    public void add(Customer customer, OrderInsertDto orderDto){
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ApiException("product not found"));

        double calculate = product.getPrice() * orderDto.getQuantity();
        LocalDate localDate = LocalDate.now();

        Order order = new Order(orderDto.getQuantity(),calculate , localDate ,  "new", customer, product);

        orderRepository.save(order);
    }

    public void update(int id, int userId, OrderUpdateDto orderDto){
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ApiException("product not found"));

        Order findOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("product not found"));

        double calculate = product.getPrice() * orderDto.getQuantity();

        LocalDate localDate = LocalDate.now();

        if(!findOrder.getCustomer().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        findOrder.setQuantity(orderDto.getQuantity());
        findOrder.setTotalPrice(calculate);
        findOrder.setDateReceived(localDate);
        findOrder.setProduct(product);
        orderRepository.save(findOrder);
    }

    public void delete(int id, int userId){
        Order findOrder = orderRepository.findById(id).orElseThrow(() -> new ApiException("order not found"));

        if(!findOrder.getCustomer().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        if(findOrder.getStatus().equals("progress") || findOrder.getStatus().equals("completed"))
            throw new ApiException("order cannot deleted");

        orderRepository.delete(findOrder);
    }

    public void updateStatus(int id, String status){
        Order findOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("order not found"));

        LocalDate localDate = LocalDate.now();

        if(!status.equals("inProgress") && !status.equals("completed"))
            throw new ApiException("status must to be inProgress or completed");

        findOrder.setStatus(status);
        findOrder.setDateReceived(localDate);
        orderRepository.save(findOrder);
    }

    public Order getOrderById(Integer userId, Integer orderId){
        Order order = orderRepository.findOrderByIdAndCustomer_Id(orderId, userId);

        if(order == null)
            throw new ApiException("order not found");

        return order;

    }

}
