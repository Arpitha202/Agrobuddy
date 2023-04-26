package com.stackroute.cartservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.cartservice.modal.UserDao;
import com.stackroute.cartservice.modal.UserDto;

@Repository
public interface UserRepository extends MongoRepository<UserDao, String> {


   
}