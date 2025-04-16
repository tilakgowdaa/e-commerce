package com.tandemloop.payment.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        String id,
        @NotNull(message = "FirstName is required")
        String firstName,
        @NotNull(message = "LastName is required")
        String lastName,
        @Email(message = "email should be formated")
        @NotNull(message = "email cannot be null")
        String email
) {
}
