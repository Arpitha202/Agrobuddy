package com.stackroute.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.authenticationservice.model.UserDao;

@Repository
public interface UserRepository extends JpaRepository<UserDao, String> {
    UserDao findByUsername(String username);
}
