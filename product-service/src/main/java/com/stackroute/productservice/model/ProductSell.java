package com.stackroute.productservice.model;

import java.io.Serializable;
import java.util.UUID;


import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Product_Sell")
public class ProductSell implements Serializable {

	@Id
	private String productId = UUID.randomUUID().toString();
	private String productName;
	private String sellerEmail;
	private double productPrice;
	private String description;
	private Binary productImage;
}
