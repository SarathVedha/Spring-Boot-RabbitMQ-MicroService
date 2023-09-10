package com.vedha.email.consumer;

import com.vedha.email.dto.OrderEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailConsumer {

    // Need to Config Message Convertor for json in config
    @RabbitListener(queues = "${rabbitmq.queue.email.name}")
    public void consumeOrderEmail(OrderEventDTO orderEventDTO) {

        log.debug("Consumed Message From Order Email Queue : " + orderEventDTO);
    }
}
