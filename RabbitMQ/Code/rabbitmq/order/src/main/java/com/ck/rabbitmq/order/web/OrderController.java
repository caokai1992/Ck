package com.ck.rabbitmq.order.web;

import com.ck.rabbitmq.order.entity.OrderInfo;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/place/{order}", method = RequestMethod.GET)
    public String placeAnOrder(@PathVariable(value = "order") String order){
        OrderInfo orderInfo = new OrderInfo();
        String random = UUID.randomUUID().toString();
        orderInfo.setId(UUID.randomUUID().toString());
        orderInfo.setOrderInfo(order);
        orderInfo.setOrderNo(random);

        System.out.println("订单已经生成，订单号为：" + random);
        System.out.println("将订单推送到仓储系统和财务系统");

        rabbitTemplate.convertAndSend("order_exchange","orderStock_queue", orderInfo, new CorrelationData());
        rabbitTemplate.convertAndSend("orderFinancial_queue", orderInfo);
        return random;
    }
}
