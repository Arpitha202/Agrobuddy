package com.stackroute.cartservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.cartservice.consumerConfig.ConsumerConfig;
import com.stackroute.cartservice.modal.ProductDto;
import com.stackroute.cartservice.modal.UserDto;
import com.stackroute.cartservice.serviceImpl.ProductServiceImpl;


@Component
public class Consumer {
	
    @Autowired
    private ProductServiceImpl productServiceImpl;
    
    @RabbitListener(queues =  ConsumerConfig.QUEUE)
    public void consumeLoanDetailsFromQueue(UserDto userDetails) {
        System.out.println("User details recieved from queue : " + userDetails);
        productServiceImpl.save(userDetails);
    }
    
    @RabbitListener(queues =  ConsumerConfig.QUEUE1)
    public void producerLoanDetailsFromQueue(ProductDto productDetails) {
        System.out.println("User details recieved from queue : " + productDetails);
        productServiceImpl.addProduct(productDetails);
    }

}
