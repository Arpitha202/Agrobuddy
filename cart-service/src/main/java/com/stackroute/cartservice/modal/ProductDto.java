package com.stackroute.cartservice.modal;

import org.bson.types.Binary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductDto {
	
	private String productId;
	private String productName;
	private String sellerEmail;
	private double productPrice;
	private String description;
	private Binary productImage;
}
