package com.ck.rabbitmq.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    final static String orderStockQueueName = "orderStock_queue";
    final static String orderFinancialQueueName = "orderFinancial_queue";
    final static String orderExchangeName = "order_exchange";

    /**
     * 初始化订单-仓储系统消息队列
     *
     * @return
     */
    @Bean(name = "orderStockQueue")
    Queue orderStockQueue() {
        return new Queue(orderStockQueueName, false);
    }

    /**
     * 初始化订单-财务系统消息队列
     *
     * @return
     */
    @Bean
    Queue orderFinancialQueue() {
        return new Queue(orderFinancialQueueName, false);
    }

    /**
     * 初始化订单交换器，使用topic模式
     *
     * @return
     */
    @Bean
    TopicExchange orderTopicExchange() {
        return new TopicExchange(orderExchangeName, false, false);
    }

    /**
     * 将队列和交换器绑定，并定义匹配规则
     *
     * @param orderStockQueue
     * @param orderTopicExchange
     * @return
     */
    @Bean
    Binding bindingOrderStock(Queue orderStockQueue, TopicExchange orderTopicExchange) {
        return BindingBuilder.bind(orderStockQueue).to(orderTopicExchange).with(orderStockQueueName);
    }

    /**
     * 将队列和交换器绑定，并定义匹配规则
     *
     * @param orderFinancialQueue
     * @param orderTopicExchange
     * @return
     */
    @Bean
    Binding bindingOrderFinancial(Queue orderFinancialQueue, TopicExchange orderTopicExchange) {
        return BindingBuilder.bind(orderFinancialQueue).to(orderTopicExchange).with(orderFinancialQueueName);
    }

//    @Bean
//    ConnectionFactory connectionFactory(CachingConnectionFactory connectionFactory){
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }

//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("消息发送成功，ack:" + ack + ",cause:"+ cause);
//            }
//        });
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                System.out.println("消息发送失败，replyCode:" + replyCode + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
//            }
//        });
//        return rabbitTemplate;
//    }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(@Qualifier("orderStockQueue") Queue orderStockQueue){
//        simpleMessageListenerContainer.setQueues(orderStockQueue);
//
//    }
}