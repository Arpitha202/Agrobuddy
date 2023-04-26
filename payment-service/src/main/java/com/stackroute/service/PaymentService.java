package com.stackroute.service;

import com.razorpay.RazorpayException;
import com.stackroute.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Payment addPayment(Payment payment) throws RazorpayException;

//    String getPaymentByOrderId(String orderId);

    Payment updatePayment(Payment payment);
    List<Payment> getAllPayment();
}
