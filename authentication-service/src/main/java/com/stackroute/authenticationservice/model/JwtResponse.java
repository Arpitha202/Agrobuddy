package com.stackroute.authenticationservice.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;
	private final String userName;
	
	public JwtResponse(String jwtToken, String userName) {
		super();
		this.jwtToken = jwtToken;
		this.userName = userName;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getJwtToken() {
		return jwtToken;
	}


	public String getUserName() {
		return userName;
	}
}
