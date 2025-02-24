package com.stackroute.cartservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.cartservice.modal.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
