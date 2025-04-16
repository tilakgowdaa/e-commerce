package com.tandemloop.order.orderline;

import com.tandemloop.order.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;
    private Integer productId;
    private double quantity;
}
