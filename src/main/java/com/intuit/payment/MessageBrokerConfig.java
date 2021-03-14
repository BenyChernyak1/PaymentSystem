package com.intuit.payment;

import com.intuit.payment.engine.RiskEngine;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfig {

    @Value("${service.rabbitmq.exchange.name}")
    private String topicExchangeName;

    @Value("${service.rabbitmq.queue.name}")
    private String paymentQueueName;

    @Value("${service.rabbitmq.routing.key}")
    private String paymentRoutingKey;

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitPassword;

    @Value("${spring.rabbitmq.port}")
    private String rabbitPort;

    @Value("${listener.risk.engine.method.name}")
    private String riskEngineMethodName;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost);
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPassword);
        connectionFactory.setPort(Integer.parseInt(rabbitPort));
        try {
            connectionFactory.createConnection();
        } catch (AmqpException e) {
            e.printStackTrace();
        }
        return connectionFactory;
    }

    @Bean
    Queue queue() {
        return new Queue(paymentQueueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(paymentRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(paymentQueueName);
        container.setMessageListener(listenerAdapter);
        container.setMaxConcurrentConsumers(1);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RiskEngine receiver) {
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver, riskEngineMethodName);
        listenerAdapter.setMessageConverter(jsonMessageConverter());
        return  listenerAdapter;
    }
}
