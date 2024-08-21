package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Operations related to books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", description = "Retrieve a book by its ID")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Create a new book entry")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book by ID", description = "Update an existing book entry")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID", description = "Delete a book by its ID")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
