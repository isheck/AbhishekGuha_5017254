package com.bookstore.controller;

import com.bookstore.model.Customer;
import com.bookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "Operations related to customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by ID", description = "Retrieve a customer by its ID")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new customer", description = "Create a new customer entry")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer by ID", description = "Update an existing customer entry")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer)
                .map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer by ID", description = "Delete a customer by its ID")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
