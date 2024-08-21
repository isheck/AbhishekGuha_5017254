package com.bookstore.mapper;

import com.bookstore.model.Customer;
import com.bookstore.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPassword(customer.getPassword());
        return customerDTO;
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        return customer;
    }
}
