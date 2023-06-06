package com.example.project28.Services;

import com.example.project28.DTOs.CustomerDto;
import com.example.project28.Exceptions.ApiException;
import com.example.project28.Models.Customer;
import com.example.project28.Repsitories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDto getAll(Integer userId){
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new ApiException("customer not found"));

        CustomerDto customerDto = new CustomerDto(customer.getUsername(), customer.getRole(), customer.getOrders());

        return customerDto;
    }

    public void add(Customer customer){
        String hash = new BCryptPasswordEncoder().encode(customer.getPassword());
        customer.setPassword(hash);
        customer.setRole("CUSTOMER");
        customerRepository.save(customer);
    }

    public void update(int customerId, Customer customer){
        Customer findCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ApiException("customer not found"));

        String hash = new BCryptPasswordEncoder().encode(customer.getPassword());
        findCustomer.setPassword(hash);
        customerRepository.save(findCustomer);
    }

    public void delete(int customerId){
        Customer findCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ApiException("customer not found"));

        customerRepository.delete(findCustomer);
    }
}
