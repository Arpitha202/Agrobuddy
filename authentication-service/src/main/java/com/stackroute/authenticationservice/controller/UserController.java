package com.stackroute.authenticationservice.controller;
import com.stackroute.authenticationservice.model.UserDao;
import com.stackroute.authenticationservice.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String getEmployees() {
        return "Welcome to your dashboard";
    }

    @GetMapping("/user/{username}")
    public UserDao getUser(@PathVariable("username") String username){
        return this.userDetailsService.getUser(username);
    }
}
