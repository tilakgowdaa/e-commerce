package com.tandemloop.notification.kafka.payment;


import com.tandemloop.notification.kafka.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(

        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String email
) {
}
