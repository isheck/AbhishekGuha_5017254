package com.bookstore.assembler;

import com.bookstore.controller.CustomerController;
import com.bookstore.model.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerResourceAssembler {

    public EntityModel<Customer> toModel(Customer customer) {
        EntityModel<Customer> customerResource = EntityModel.of(customer);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel();
        customerResource.add(selfLink);

        return customerResource;
    }
}
