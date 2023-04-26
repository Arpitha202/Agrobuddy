package com.stackroute.productservice.repository;

import com.stackroute.productservice.model.ProductSell;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends MongoRepository<ProductSell, String>{

   // @Query(value="{ 'productName' : ?0 }")
   ProductSell findByProductName(String productName);
  
    Boolean existsByProductName(String productName);
    Boolean existsByProductId(String productId);
    List<ProductSell> findBySellerEmail(String sellerEmail);

	

	

}
