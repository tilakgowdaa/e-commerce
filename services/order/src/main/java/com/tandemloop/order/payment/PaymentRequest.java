package com.tandemloop.order.payment;

import com.tandemloop.order.customer.CustomerResponse;
import com.tandemloop.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String OrderRefernce,
        CustomerResponse customer
) {
}
