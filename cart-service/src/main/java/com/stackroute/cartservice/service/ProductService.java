package com.stackroute.cartservice.service;

import java.util.List;

import com.stackroute.cartservice.exception.ProductNotFoundException;
import com.stackroute.cartservice.modal.Cart;
import com.stackroute.cartservice.modal.Product;
import com.stackroute.cartservice.modal.ProductDto;
import com.stackroute.cartservice.modal.UserDao;
import com.stackroute.cartservice.modal.UserDto;

public interface ProductService {
    Cart saveProduct(Cart product) throws Exception;
    Cart getProductById(String productId) throws ProductNotFoundException;
    List<Product> getAllProducts(String email);
    boolean deleteAProduct(String email, String productId) throws ProductNotFoundException;
    Cart updateAProduct(String email, String productId, int qty) throws ProductNotFoundException;
    List deleteAllProducts(String email) throws ProductNotFoundException;
    Cart updateQuantity(Cart product,String productId) throws Exception;
    Cart getCartByEmail(String emailId);
    UserDao save(UserDto user);
	Product addProduct(ProductDto product);
}
