package com.stackroute.controller;

import java.util.List;

import com.stackroute.model.Payment;
import com.stackroute.repository.PaymentRepository;
import com.stackroute.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {


	@Autowired
	PaymentService paymentService;

	@PostMapping(value = "/addpayment")//localhost:9393/payment/payment
	ResponseEntity<Payment> addPayment(@RequestBody Payment payment) throws RazorpayException{
		return new ResponseEntity<Payment>(paymentService.addPayment(payment),HttpStatus.OK);
	}

	@PutMapping(value = "/update")//localhost:9393/payment/payment/update
	ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) throws RazorpayException{
		return new ResponseEntity<Payment>(paymentService.updatePayment(payment),HttpStatus.OK);
	}
    @GetMapping(value = "/allPayment")//localhost:9393/payment/allPayment
	ResponseEntity<List<Payment>> getAllPayment(){
		return  new ResponseEntity<>(paymentService.getAllPayment(),HttpStatus.OK);
	}


}
