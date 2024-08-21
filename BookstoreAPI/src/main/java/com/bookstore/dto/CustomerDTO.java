package com.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class CustomerDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;

    // Getters and Setters
    // ...
}
