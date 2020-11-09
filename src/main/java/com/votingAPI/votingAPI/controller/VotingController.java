package com.votingAPI.votingAPI.controller;

import com.votingAPI.votingAPI.configureRabbitMq.ConfigureRabbitMq;
import com.votingAPI.votingAPI.vote.Vote;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class VotingController {
    private final RabbitTemplate rabbitTemplate;

    public VotingController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    String RESULT_OK = "{\"result\" : \"ok\"}";
    String RESULT_FAILED = "{\"result\" : \"failed\"}";

    @PostMapping("/vote")
    public String Vote(@RequestBody Vote vote) {
        String message = RESULT_OK;
        try {
            rabbitTemplate.convertAndSend(ConfigureRabbitMq.EXCHANGE_NAME,
                    ConfigureRabbitMq.ROUTING_KEY, vote.getId());
        }
        catch(Exception e) {
            message = RESULT_FAILED;
        }
        return message;
    }
}
