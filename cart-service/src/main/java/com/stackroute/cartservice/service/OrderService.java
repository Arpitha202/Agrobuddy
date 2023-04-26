package com.stackroute.cartservice.service;

import java.util.List;

import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.cartservice.modal.Order;

@Service
public interface OrderService {
	
    Order saveOrder(Order order);
    List<Order>getAllOrder();
    Order updateById(Order order, String orderId);
    Order findById(String orderId);
    void deleteById(String orderId);
}
