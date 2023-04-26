package com.stackroute.cartservice.modal;



import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "addtocart")
public class Product {

	private String productId;
	private String productName;
	private String sellerEmail;
	private double productPrice;
	private String description;
	private Binary productImage;
}
