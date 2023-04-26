package com.stackroute.productservice.controller;



import java.util.List;


import com.stackroute.productservice.model.ProductRent;
import com.stackroute.productservice.service.ProductRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/* This is the controller class here I am calling postmapping,getmapping,
		putmapping,and deletemapping*/

@RestController
@RequestMapping(value="/api")
@CrossOrigin("*")
public class RentController {

	
	@Autowired
	ProductRentService rentService;
	
	@PostMapping(value="/addRentProduct")//localhost:8092/api/addRentProduct
	public ResponseEntity<ProductRent>  addProduct(@RequestBody ProductRent productRentDto){
		return new ResponseEntity<ProductRent>(rentService.addProduct(productRentDto),HttpStatus.OK);
	}
	@GetMapping(value="/rentProducts")//localhost:8092/api/rentProducts
	public ResponseEntity<List<ProductRent>>  getAllProduct(){
		return new ResponseEntity<List<ProductRent>>(rentService.getAllProduct(),HttpStatus.OK);
	}
	@GetMapping(value="/rentProduct/{rentId}")//localhost:8092/api/rentProduct/{rentId}
	public ResponseEntity<ProductRent>  getProductById(@PathVariable String rentId) {
		return new ResponseEntity<ProductRent>(rentService.getProductById(rentId),HttpStatus.OK);
	}
	@DeleteMapping(value="/deleteProduct/{rentId}")//localhost:8092/api/deleteProduct/{rentId}
	public ResponseEntity<String>  deleteProductById(@PathVariable String rentId) {
		return new ResponseEntity<String>(rentService.deleteProductById(rentId),HttpStatus.OK);
	}
	@PutMapping(value="/updateProduct/{rentId}")//localhost:8092/api/updateProduct
	public ResponseEntity<ProductRent>  updateProductById(@RequestBody ProductRent productRentDto) {
		return new ResponseEntity<ProductRent>(rentService.updateProductById(productRentDto),HttpStatus.OK);
	}
}
