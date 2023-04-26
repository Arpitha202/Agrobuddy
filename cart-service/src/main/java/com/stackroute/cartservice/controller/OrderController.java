package com.stackroute.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.stackroute.cartservice.exception.DetailsNotMatchingException;
import com.stackroute.cartservice.modal.Order;
import com.stackroute.cartservice.service.OrderService;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/v1/api")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "add")
	public ResponseEntity<Order> addOrder(@RequestBody Order order)
			throws DetailsNotMatchingException {
		ResponseEntity<Order> responseEntity;
		try {
			Order orderFromService = orderService.saveOrder(order);
			if(orderFromService != null) {
				return new ResponseEntity<Order>(orderFromService, HttpStatus.OK);
				
			}else {
				return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
			}
			
		} catch (DetailsNotMatchingException e) {
			throw new DetailsNotMatchingException("Details not found");
		}
	}

	@PutMapping(value = "order/{orderId}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable String orderId) {
		return new ResponseEntity<Order>(orderService.updateById(order,orderId), HttpStatus.OK);
	}

	@GetMapping(value = "order")
	public ResponseEntity<List<Order>> getAllOrder() {
		return new ResponseEntity<List<Order>>(orderService.getAllOrder(), HttpStatus.OK);
	}

	@GetMapping(value = "order/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
		return new ResponseEntity<Order>(orderService.findById(orderId), HttpStatus.OK);

	}

	@DeleteMapping(value = "order/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
		orderService.deleteById(orderId);
		return new ResponseEntity<String>("Order deleted successfully", HttpStatus.OK);

	}
}
