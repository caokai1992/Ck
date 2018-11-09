package com.ck.rabbitmq.financial.receiver;

import com.ck.rabbitmq.order.entity.OrderInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "orderFinancial_queue")
public class FinancialReceiver {

    @RabbitHandler
    public void financialReceiver(OrderInfo orderInfo){
        if(null != orderInfo){
            System.out.println("接受到订单信息，订单号为：" + orderInfo.getOrderNo());
            System.out.println("财务准备...");
        }
    }
}
