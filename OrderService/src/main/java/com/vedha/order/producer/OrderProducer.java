package com.vedha.order.producer;

import com.vedha.order.dto.OrderEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor()
@Slf4j
public class OrderProducer {

    // Used @RequiredArgsConstructor() spring will create object with only for final variable.
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.exchange}")
    private String orderQueueExchangeName;

    @Value("${rabbitmq.queue.order.routing-key}")
    private String orderQueueRoutingKey;

    @Value("${rabbitmq.queue.email.routing-key}")
    private String orderEmailQueueRoutingKey;

    public void sendOrderMessage(OrderEventDTO orderEventDTO) {

        log.debug("Message Send To Order Queue : " + orderEventDTO);

        rabbitTemplate.convertAndSend(orderQueueExchangeName, orderQueueRoutingKey, orderEventDTO);
    }

    public void sendOrderEmailMessage(OrderEventDTO orderEventDTO) {

        log.debug("Message Send To Order Email Queue : " + orderEventDTO);

        rabbitTemplate.convertAndSend(orderQueueExchangeName, orderEmailQueueRoutingKey, orderEventDTO);
    }

}
