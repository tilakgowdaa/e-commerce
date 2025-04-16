package com.tandemloop.order.order;

import com.tandemloop.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount must be positive")
        BigDecimal amount,
        @NotNull(message = "payment cannot be null")
        PaymentMethod paymentMethod,
        @NotNull(message = "customerId cannot be null")
        @NotEmpty(message = "customerId cannot be empty")
        @NotBlank(message = "customerId cannot be blank")
        String customerId,
        @NotEmpty(message = "you should purchase min 1 product")
        List<PurchaseRequest> products
) {
}
