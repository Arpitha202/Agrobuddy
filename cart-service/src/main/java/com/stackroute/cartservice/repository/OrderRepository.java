package com.stackroute.cartservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.cartservice.modal.Order;

public interface OrderRepository extends MongoRepository<Order, String>{

	Optional<Order> findByOrderId(String orderId);

	void deleteByOrderId(String orderId);
	
}
