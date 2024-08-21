package com.bookstore.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "title", "author", "price", "isbn" })
public class BookDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    private String author;

    @NotNull
    @Min(0)
    private Double price;

    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;

    // Constructors, getters, and setters
}
