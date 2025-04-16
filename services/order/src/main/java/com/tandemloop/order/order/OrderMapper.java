package com.tandemloop.order.order;

import com.tandemloop.order.orderline.OrderLine;
import com.tandemloop.order.orderline.OrderLineRequest;
import com.tandemloop.order.orderline.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder().id(request.id()).customerId(request.customerId()).reference(request.reference()).paymentMethod(request.paymentMethod()).totalAmount(request.amount()).build();
    }

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder().id(request.id()).productId(request.productId()).quantity(request.quantity()).order(Order.builder().id(request.orderId()).build()).build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getReference(), order.getTotalAmount(), order.getPaymentMethod(), order.getCustomerId());
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
