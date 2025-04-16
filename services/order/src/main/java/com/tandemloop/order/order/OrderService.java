package com.tandemloop.order.order;

import com.tandemloop.order.kafka.OrderConfirmation;
import com.tandemloop.order.kafka.OrderProducer;
import com.tandemloop.order.payment.PaymentClient;
import com.tandemloop.order.payment.PaymentRequest;
import com.tandemloop.order.product.ProductClient;
import com.tandemloop.order.product.PurchaseRequest;
import com.tandemloop.order.customer.CustomerClient;
import com.tandemloop.order.exception.BusinessException;
import com.tandemloop.order.orderline.OrderLineRequest;
import com.tandemloop.order.orderline.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repo;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        //check the customer(feign)
        var customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order:: No Customer found"));
        //purchase the product--> product microservices(restTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        //persist order
        var order = this.repo.save(mapper.toOrder(request));//persist order lines
        //persist orderline
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }
        //start payment process
        PaymentRequest paymentRequest=new PaymentRequest(request.amount(),request.paymentMethod(),order.getId(),order.getReference(),customer);
        paymentClient.requestOrderPayment(paymentRequest);
        //send the order conformation -->notification (kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(), request.amount(), request.paymentMethod(), customer, purchasedProducts));
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repo.findAll().stream().map(mapper::toOrderResponse).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return repo.findById(id).map(mapper::toOrderResponse).orElseThrow(() -> new BusinessException("No order found with provided id"));
    }
}
