package com.vedha.stock.consumer;

import com.vedha.stock.dto.OrderEventDTO;
import com.vedha.stock.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderConsumer {

    private final StockService stockService;

    @RabbitListener(queues = "${rabbitmq.queue.order.name}")
    public void consumeOrders(OrderEventDTO orderEventDTO) {

        log.debug("Consumed Message From Order Queue : " + orderEventDTO);

        stockService.processOrder(orderEventDTO);

    }
}
