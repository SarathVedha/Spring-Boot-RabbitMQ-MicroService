package com.vedha.order.service.impl;

import com.vedha.order.dto.OrderDTO;
import com.vedha.order.dto.OrderEventDTO;
import com.vedha.order.producer.OrderProducer;
import com.vedha.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderProducer orderProducer;

    @Override
    public String makeOrder(OrderDTO orderDTO) {

        orderDTO.setId(UUID.randomUUID().toString());

        OrderEventDTO orderEventDTO = OrderEventDTO.builder().status("PENDING").message("Order Placed SuccessFully").orderDTO(orderDTO).build();

        orderProducer.sendOrderMessage(orderEventDTO);

        orderProducer.sendOrderEmailMessage(orderEventDTO);

        return "Order Placed SuccessFully";
    }
}
