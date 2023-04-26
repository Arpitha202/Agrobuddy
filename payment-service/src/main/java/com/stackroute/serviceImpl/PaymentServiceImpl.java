package com.stackroute.serviceImpl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.exception.OrderIdNotFoundException;
import com.stackroute.model.Payment;
import com.stackroute.repository.PaymentRepository;
import com.stackroute.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment addPayment(Payment payment) throws RazorpayException {
        double productPrice = payment.getProductPrice();
        productPrice = productPrice * 100;

        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_SAEPvGZuIWyHlv", "mrz52BybeEPxRWCOE8LJ6asw");
        JSONObject options = new JSONObject();
        options.put("amount", productPrice);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");

        // creating order in razor pay by adding amount, currency and receipt details
        Order orderCreated = razorpayClient.Orders.create(options);
        payment.setStatus(orderCreated.get("status"));
        payment.setReceipt(orderCreated.get("receipt"));
        payment.setOrderId(orderCreated.get("id"));

        //converting coursePrice in double
        double amount = Double.valueOf(orderCreated.get("amount")+"");
        amount /=100; //to convert paise to INR
        payment.setProductPrice(amount);
        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment;
    }

//    @Override
//    public String getPaymentByOrderId(String orderId) {
//
//        Optional<String> findById = paymentRepository.findByOrderId(orderId);
//        if (findById.isPresent()) {
//            Payment paymentByOrderId = findById.get();
//            return paymentByOrderId;
//        } else {
//            throw new OrderIdNotFoundException("SERVICE.PAYMENT_NOT_FOUND");
//        }
//    }

    @Override
    public Payment updatePayment(Payment payment) {
        String orderId = payment.getOrderId();
        Payment payment1 = paymentRepository.findByOrderId(payment.getOrderId());
        Payment updatedPayment=null;

        payment1.setPaymentId(payment.getPaymentId());
        payment1.setStatus(payment.getStatus());
        updatedPayment = paymentRepository.save(payment1);
        return updatedPayment;

    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }
}
