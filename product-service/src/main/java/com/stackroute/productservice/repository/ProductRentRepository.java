package com.stackroute.productservice.repository;


import com.stackroute.productservice.model.ProductRent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRentRepository extends MongoRepository<ProductRent, String>{

}
