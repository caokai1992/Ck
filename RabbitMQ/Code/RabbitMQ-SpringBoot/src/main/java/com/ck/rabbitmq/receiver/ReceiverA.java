package com.ck.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "MQ_1")
public class ReceiverA {
    @RabbitHandler
    public void receiver(String msg){
        System.out.println("A receiver :" + msg);
    }
}
