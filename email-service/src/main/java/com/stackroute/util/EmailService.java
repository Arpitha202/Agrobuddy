package com.stackroute.util;

import com.stackroute.model.Email;

public interface EmailService {
	public void sendSimpleMessage( String to, String subject, String text);

	public void orderStatus(Email email);

	public void sendRegisterEmail(Email email);
}
