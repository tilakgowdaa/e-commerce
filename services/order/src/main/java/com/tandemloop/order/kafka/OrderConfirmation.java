package com.tandemloop.order.kafka;

import com.tandemloop.order.customer.CustomerResponse;
import com.tandemloop.order.order.PaymentMethod;
import com.tandemloop.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
