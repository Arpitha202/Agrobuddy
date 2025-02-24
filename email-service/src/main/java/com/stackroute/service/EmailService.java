package com.stackroute.service;

import com.stackroute.model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendRegisterEmail(Email email) { // TODO Auto-generated method

	}

	
	public void sendSimpleMessage( String to, String subject, String text) {
		try {
			System.out.println("sendSimpleMessage start");
			SimpleMailMessage message = new SimpleMailMessage(); 
		    message.setFrom("agrobuddy124@gmail.com");
		    message.setTo(to); 
		    message.setSubject(subject); 
		    message.setText(text);
		    emailSender.send(message);
		    System.out.println("sendSimpleMessage end");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void orderStatus(Email email) { // TODO Auto-generated method stub

	}

}
