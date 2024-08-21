package com.bookstore.service;

import com.bookstore.dto.CustomerDTO;
import com.bookstore.model.Customer;
import com.bookstore.repository.CustomerRepository;
import com.bookstore.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(customerMapper::toDTO).orElse(null);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setId(id);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(updatedCustomer);
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
