package com.stackroute.cartservice.modal;

import java.util.List;

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
@Document(collection = "addtocart") // mention table name here
public class Cart {

	private String buyerEmail;
	private int productQuantity;
	private List<Product> productList;

	public List<Product> getProductList() {
		return this.productList;
	}

}