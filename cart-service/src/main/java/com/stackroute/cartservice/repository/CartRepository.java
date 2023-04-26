package com.stackroute.cartservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.cartservice.modal.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
	void deleteByBuyerEmail(String buyerEmail);
	Optional<Cart> findByBuyerEmail(String buyerEmail);
}
