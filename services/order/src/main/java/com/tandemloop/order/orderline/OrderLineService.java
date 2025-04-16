package com.tandemloop.order.orderline;

import com.tandemloop.order.order.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository repo;
    private final OrderMapper mapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order=mapper.toOrderLine(orderLineRequest);
        return repo.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer id) {
       return repo.findAll().stream().map(mapper::toOrderLineResponse).collect(Collectors.toList());
    }
}
