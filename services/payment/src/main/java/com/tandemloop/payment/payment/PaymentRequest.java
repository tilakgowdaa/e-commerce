package com.tandemloop.payment.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod  paymentMethod,
        Integer orderId,
        String OrderRefernce,
        Customer customer
) {
}
