package com.vedha.stock.producer;

import com.vedha.stock.dto.OrderEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockProducer {

    private final RabbitTemplate rabbitTemplate;

    // Using Existing Email Queues Binding Because In Order Service Already Queue Initialized
    @Value("${rabbitmq.queue.exchange.name}")
    private String orderQueueExchange;

    @Value("${rabbitmq.queue.email.routing-key}")
    private String orderEmailQueueRoutingKey;

    public void sendOrderEmailMessage(OrderEventDTO orderEventDTO) {

        log.debug("Sending Message to Order Email Queue : " + orderEventDTO);

        rabbitTemplate.convertAndSend(orderQueueExchange, orderEmailQueueRoutingKey, orderEventDTO);
    }
}
