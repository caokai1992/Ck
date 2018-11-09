package com.ck.rabbitmq.stock.receiver;

import com.ck.rabbitmq.order.entity.OrderInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "orderStock_queue")
public class StockReceiver {

    @RabbitHandler
    public void stockReceiver(OrderInfo orderInfo){
        if(null != orderInfo){
            System.out.println("接受到新订单消息，订单号为：" + orderInfo.getOrderNo());
            System.out.println("订单：" + orderInfo.getOrderNo() + "进行库存准备");
            System.out.println("正在检测是否有可用库存...");
        }
    }
}
