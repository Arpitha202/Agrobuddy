package com.stackroute.cartservice.modal;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Id;

import org.springframework.data.annotation.LastModifiedDate;
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
public class Order {
	
	@Id
	private String id;
    private String orderId = UUID.randomUUID().toString();
    @LastModifiedDate
    private Date orderDate;
    private Date deliveryDate;
    private List<Cart>cart;
}

//private String userEmail;
//private double price;