package com.votingAPI.votingAPI.configureRabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureRabbitMq {
    public static final String EXCHANGE_NAME = "voteExchange";
    public static final String QUEUE_NAME = "voteQueue";
    public static final String ROUTING_KEY = "rt_key_vote";

    @Bean
    Queue createQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue q, TopicExchange exchange){
        return BindingBuilder.bind(q).to(exchange).with(ROUTING_KEY);
    }
}
