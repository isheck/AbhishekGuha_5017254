package com.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Version;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @NotNull
    @Size(min = 1, max = 255)
    private String author;

    @Min(0)
    private double price;

    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;

    @Version
    private Long version;

    // Getters and Setters
    // ...
}
