package com.tandemloop.order.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest( Integer id, Integer orderId, @NotNull(message = "Product id required") Integer productId,
                               @Positive(message = "quantity must be positive") double quantity) {
}
