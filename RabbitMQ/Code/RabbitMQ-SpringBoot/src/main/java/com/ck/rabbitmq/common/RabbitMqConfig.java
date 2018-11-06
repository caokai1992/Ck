package com.ck.rabbitmq.common;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue queue1(){
        return new Queue("MQ_1");
    }
}