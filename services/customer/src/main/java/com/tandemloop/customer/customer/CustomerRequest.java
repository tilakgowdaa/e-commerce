package com.tandemloop.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "firstName cannot be null")
         String firstName,
         @NotNull(message = "LastName cannot be null")
         String lastName,
         @NotNull(message = "email required")
         @Email(message = "Invalid email")
         String email,
         Address address
) {
}
