package com.stackroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.model.Email;
import com.stackroute.service.EmailService;



@RestController
@RequestMapping("/email")
@CrossOrigin("*")
public class EmailController {
    @Autowired
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/welcome")
    public String welcome(){
    	emailService.sendSimpleMessage("agrobuddy124@gmail.com","order placed","susccfully done");
        return "this is my email api";
    }

    @PostMapping("/sendregisteremail")
    @CrossOrigin(origins = "http://localhost:4200")
    public String sendRegisterEmail(@RequestBody String to){
    	
       this.emailService.sendSimpleMessage(to, "Registration Confirmation!!", "You have Successfully Registred to agrobuddy!");
        
        return"Successfully Sent!";
    }
    
    @PostMapping("/orderstatus")
    @CrossOrigin(origins = "http://localhost:4200")
    public String orderStatus(@RequestBody String to){
        
       this.emailService.sendSimpleMessage(to, "Order Status!", "Your order has been Successfully done to agrobuddy!");

       return"order placed Susccfully!";
}
    
    @PostMapping("/sendsimpleemail")
    @CrossOrigin(origins = "http://localhost:4200")
    public String sendsimpleemail(@RequestBody String to){
    	System.out.println(to);
       
       this.emailService.sendSimpleMessage(to, "Registration Confirmation!!", "You have Successfully Registred to agrobuddy!");

        return"Successfully Sent!";
    }
}