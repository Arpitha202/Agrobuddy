package com.stackroute.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class EmailServiceImpl {
@Autowired
	private JavaMailSender emailSender;

public void sendSimpleMessage( String to, String subject, String text) {
	
	SimpleMailMessage message = new SimpleMailMessage(); 
    message.setFrom("agrobuddy124@gmail.com");
    message.setTo(to); 
    message.setSubject(subject); 
    message.setText(text);
    emailSender.send(message);
}
	
	
}
