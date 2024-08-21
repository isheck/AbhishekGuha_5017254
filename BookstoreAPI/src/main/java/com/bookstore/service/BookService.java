package com.bookstore.service;

import com.bookstore.dto.BookDTO;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.mapper.BookMapper;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    // Create a new book
    @Transactional
    public BookDTO createBook(@Valid BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    // Get a book by ID
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID " + id));
        return bookMapper.toDTO(book);
    }

    // Get all books with pagination
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDTO);
    }

    // Update a book
    @Transactional
    public BookDTO updateBook(Long id, @Valid BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID " + id));
        // Update fields
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setIsbn(bookDTO.getIsbn());
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    // Delete a book
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID " + id));
        bookRepository.delete(book);
    }
}
