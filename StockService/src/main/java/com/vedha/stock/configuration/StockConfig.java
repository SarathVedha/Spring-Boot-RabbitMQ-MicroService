package com.vedha.stock.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfig {

    @Value("${rabbitmq.queue.exchange.name}")
    private String queueExchange;

    @Value("${rabbitmq.queue.email.name}")
    private String orderEmailQueueName;

    @Value("${rabbitmq.queue.email.routing-key}")
    private String orderEmailQueueRoutingKey;

    // Using Existing Email Queues Binding, Because In Order Service Already Queue Initialized
    // initialization not required for safety you can initialize same here

    // Queue Exchange
    @Bean
    TopicExchange initializeQueueExchange() {

        return new TopicExchange(queueExchange);
    }

    // Email Queue
    @Bean
    Queue initializeEmailQueue() {

        return new Queue(orderEmailQueueName);
    }

    // Email Queue Binding with exchange and routing key
    @Bean
    Binding initializeEmailQueueRoutingKey() {

        return BindingBuilder.bind(initializeEmailQueue()).to(initializeQueueExchange()).with(orderEmailQueueRoutingKey);
    }

    // For Consuming Json as Java DTO need to define Message Convertor
    @Bean
    MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AmqpTemplate initializeRabbitMqTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }
}
