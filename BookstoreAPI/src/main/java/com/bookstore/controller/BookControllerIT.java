package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
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
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(new BookController()).build();
    }

    @Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/books/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddBook() throws Exception {
        Book book = new Book(1L, "Title", "Author", 19.99, "123456789");
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"Title\",\"author\":\"Author\",\"price\":19.99,\"isbn\":\"123456789\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book(1L, "Updated Title", "Updated Author", 29.99, "987654321");
        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\",\"author\":\"Updated Author\",\"price\":29.99,\"isbn\":\"987654321\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());
    }
}
