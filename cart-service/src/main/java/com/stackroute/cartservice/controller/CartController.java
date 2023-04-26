package com.stackroute.cartservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.cartservice.exception.ProductNotFoundException;
import com.stackroute.cartservice.modal.Cart;
import com.stackroute.cartservice.service.ProductService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

	private ResponseEntity responseEntity;
	private ProductService service;

	@Autowired
	public CartController(ProductService service) {
		this.service = service;
	}

	@PostMapping("/addProductToCart")
	public ResponseEntity addProductToCart(@RequestBody Cart cart) throws Exception {
		System.out.println(cart);
		responseEntity = new ResponseEntity(service.saveProduct(cart), HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/showAllProductInCart")
	public ResponseEntity showAllProductInCart(@RequestParam String email) {
		try {
			responseEntity = new ResponseEntity(service.getAllProducts(email), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@DeleteMapping("/deleteProductByProductId")
	public ResponseEntity<?> deleteProductByProductId(@RequestParam("productId") String productId,
			@RequestParam String buyerEmail) throws ProductNotFoundException {
		try {
			service.deleteAProduct(buyerEmail, productId);
			responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
		} catch (ProductNotFoundException e) {

			throw new ProductNotFoundException();

		} catch (Exception exception) {
			String ex = exception.getMessage();
			System.out.println(exception.getMessage());
			responseEntity = new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@DeleteMapping("/deleteAllProducts")
	public ResponseEntity<?> deleteAllProducts(@RequestParam String buyerEmail) throws ProductNotFoundException {
		try {
			responseEntity = new ResponseEntity(service.deleteAllProducts(buyerEmail), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@GetMapping("/getProductById")
	public ResponseEntity getProductById(@RequestParam("productId") String productId) throws ProductNotFoundException {
		responseEntity = new ResponseEntity(service.getProductById(productId), HttpStatus.OK);
		return responseEntity;

	}

	@GetMapping("/getProductByEmail")
	public ResponseEntity getProductByEmail(@RequestParam String buyerEmail) {
		responseEntity = new ResponseEntity(service.getCartByEmail(buyerEmail), HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/updateProductWithProductId")
	public ResponseEntity<?> updateProductWithProductId(@RequestParam String productId, @RequestParam int qty,
			@RequestParam String email) throws ProductNotFoundException {
		return new ResponseEntity(service.updateAProduct(email, productId, qty), HttpStatus.OK);
	}
}
