package com.stackroute.productservice.controller;


import java.util.List;


import com.stackroute.productservice.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.stackroute.productservice.model.ProductSell;
import com.stackroute.productservice.producerConfig.ProducerConfig;
import com.stackroute.productservice.service.ProductSellService;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
public class SellController {

	@Autowired
	ProductSellService service;

	@Autowired
	private RabbitTemplate template;



	@PostMapping(value = "/addproduct") // localhost:8092/product/addproduct
	public ResponseEntity<ProductSell> addProduct(@RequestBody ProductSell productSell) {
		service.addProduct(productSell);
		template.convertAndSend(ProducerConfig.EXCHANGE1,ProducerConfig.ROUTING_KEY1,productSell);
		return new ResponseEntity<ProductSell>(productSell, HttpStatus.OK);
	}
	@PostMapping(value="/addimage/{productId}")//localhost:8092/url/addimage/{productId}
	public ResponseEntity<Object> addProductImage(@PathVariable String productId,@RequestParam(value = "multipartFile") MultipartFile multipartFile) throws ProductNotFoundException {
		ResponseEntity<Object> responseEntity;
		log.info("In controlller class to Add Info");

		ProductSell productFromService= (ProductSell) service.addProductImage(productId,multipartFile);
		responseEntity = new ResponseEntity<Object>(productFromService, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(value = "/products") // localhost:8092/product/products
	public ResponseEntity<List<ProductSell>> getAllProduct() {
		return new ResponseEntity<List<ProductSell>>(service.getAllProduct(), HttpStatus.OK);
	}

	@GetMapping(value = "/product/{productId}") // localhost:8092/product/product/{productId}
	public ResponseEntity<ProductSell> getProductById(@PathVariable String productId) {
		return new ResponseEntity<ProductSell>(service.getProductById(productId), HttpStatus.OK);
	}

	@GetMapping(value = "/getbyemail/{sellerEmail}") // localhost:8092/product/getbyemail/{sellerEmail}
	public ResponseEntity<List<ProductSell>> getProductBySellerEmail(@PathVariable String sellerEmail) {
		return new ResponseEntity<List<ProductSell>>(service.getProductBySellerEmail(sellerEmail), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteproduct/{productId}") // localhost:8092/product/deleteproduct/{productId}
	public ResponseEntity<String> deleteProductById(@PathVariable String productId) {
		return new ResponseEntity<String>(service.deleteProductById(productId), HttpStatus.OK);
	}

	@PutMapping(value = "/updateproduct") // localhost:8092/product/updateproduct
	public ResponseEntity<ProductSell> updateProductById(@RequestBody ProductSell productSell) {
		return new ResponseEntity<ProductSell>(service.updateProductById(productSell), HttpStatus.OK);
	}
}
