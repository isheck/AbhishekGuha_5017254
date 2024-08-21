package com.bookstore.assembler;

import com.bookstore.controller.BookController;
import com.bookstore.model.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookResourceAssembler {

    public EntityModel<Book> toModel(Book book) {
        EntityModel<Book> bookResource = EntityModel.of(book);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(book.getId())).withSelfRel();
        bookResource.add(selfLink);

        return bookResource;
    }
}
