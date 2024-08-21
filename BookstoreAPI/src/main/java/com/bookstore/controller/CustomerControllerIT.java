package com.bookstore.controller;

import com.bookstore.model.Customer;
import com.bookstore.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController()).build();
    }

    @Test
    void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/customers/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddCustomer() throws Exception {
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com");
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = new Customer(1L, "Jane Doe", "jane.doe@example.com");
        mockMvc.perform(put("/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk());
    }
}
