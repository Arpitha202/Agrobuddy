package com.stackroute.productservice.service;


import com.stackroute.productservice.model.ProductSell;

import org.springframework.web.multipart.MultipartFile;



import java.util.List;




public interface ProductSellService {
	public Object addProduct(ProductSell productSell) ;
	//Object addProductImage(String productName, MultipartFile multipartFile);
	Object addProductImage(String productId, MultipartFile multipartFile);
	public List<ProductSell> getAllProduct();
	public ProductSell getProductById(String productId);
	
	public String deleteProductById(String productId);
	public ProductSell updateProductById(ProductSell productSell) ;
	List<ProductSell> getProductBySellerEmail(String sellerEmail);
	//ProductSell saveProduct(ProductSell productSell,MultipartFile file) throws IOException;
}
