package com.vedha.order.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Value("${rabbitmq.queue.exchange}")
    private String queueExchange;

    @Value("${rabbitmq.queue.order.name}")
    private String orderQueueName;

    @Value("${rabbitmq.queue.order.routing-key}")
    private String orderQueueRoutingKey;

    @Value("${rabbitmq.queue.email.name}")
    private String orderEmailQueueName;

    @Value("${rabbitmq.queue.email.routing-key}")
    private String orderEmailQueueRoutingKey;

    /*
     * Note, This Three Bean Will be Auto Configured by spring.
     * ConnectionFactory
     * RabbitTemplate
     * RabbitAdmin
     * */

    // Exchange
    @Bean
    TopicExchange initializeQueueExchange() {

        return new TopicExchange(queueExchange);
    }

    // Order Queue
    @Bean
    Queue initializeOrderQueue() {

        return new Queue(orderQueueName);
    }

    // Binding Order queue and exchange with Order routing key
    @Bean
    Binding initializeQueueOrderRoutingKey() {

        return BindingBuilder.bind(initializeOrderQueue()).to(initializeQueueExchange()).with(orderQueueRoutingKey);
    }

    // Email Queue
    @Bean
    Queue initializeOrderEmailQueue() {

        return new Queue(orderEmailQueueName);
    }

    // Binding Email queue and exchange with Email routing key
    @Bean
    Binding initializeQueueEmailRoutingKey() {

        return BindingBuilder.bind(initializeOrderEmailQueue()).to(initializeQueueExchange()).with(orderEmailQueueRoutingKey);
    }

    // Message Convertor for json
    @Bean
    MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    // Setting convertor to template
    @Bean
    AmqpTemplate initializeRabbitMQTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }
}
