package com.stackroute.productservice.service;


import com.stackroute.productservice.model.ProductRent;

import java.io.IOException;
import java.util.List;





public interface ProductRentService {
	public ProductRent addProduct(ProductRent productRentDto);
	public List<ProductRent> getAllProduct();
	public ProductRent getProductById(String rentId);
	public String deleteProductById(String rentId);
	public ProductRent updateProductById(ProductRent productRentDto);

}
