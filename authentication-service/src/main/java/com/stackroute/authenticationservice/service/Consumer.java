package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.config.ConsumerConfig;
import com.stackroute.authenticationservice.model.UserDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private JwtUserDetailsService service;

   // @RabbitListener(queues = ConsumerConfig.QUEUE1)
    @RabbitListener(queues =  ConsumerConfig.QUEUE)
    public void consumeLoanDetailsFromQueue(UserDto userDetails) {
        System.out.println("User details recieved from queue : " + userDetails);
        service.save(userDetails);
    }

}
