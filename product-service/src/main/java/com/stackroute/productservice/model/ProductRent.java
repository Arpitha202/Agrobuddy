package com.stackroute.productservice.model;




import java.util.UUID;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Data
@Document(collection="Product_Rent")
public class ProductRent {


	@Id
	private  String rentId=UUID.randomUUID().toString();
	private  String rentProductName;
	private  String renterEmail;
	private  double rentProductPrice_PerDay;
	private  String productDescription;
	private  String rentProductImage1;
}
