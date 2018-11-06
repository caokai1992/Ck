package com.ck.rabbitmq.web;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rebbit")
public class SendController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * RebbitMQ
     */
    @RequestMapping(value="/send", method = RequestMethod.GET)
    public String send() {
        String content = new String();
        for(int i = 0; i < 10; i++){
            content = "Date:" + new Date();
            amqpTemplate.convertAndSend("MQ_1", content);
        }
        return content;
    }
}
