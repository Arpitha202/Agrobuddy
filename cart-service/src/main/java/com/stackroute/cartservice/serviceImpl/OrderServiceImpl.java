package com.stackroute.cartservice.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.cartservice.exception.OrderNotFoundException;
import com.stackroute.cartservice.modal.Order;
import com.stackroute.cartservice.repository.OrderRepository;
import com.stackroute.cartservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order saveOrder(Order order) {
		log.info("Order List,service; save Order");
		try {
			if (order != null) {
				Date date = new Date();
				order.setOrderDate(date);
				order.setDeliveryDate(date);
				order.setId(order.getId());
				return orderRepository.save(order);
			} else {
				throw new OrderNotFoundException("No order found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Order> getAllOrder() {
		log.info("Order List,service; fetch all orders");
		return orderRepository.findAll();
	}

	@Override
	public Order updateById(Order order, String orderId) {
		log.info(" Order List,service; update order with orderId ");
		Optional<Order> orderFromDb = orderRepository.findByOrderId(orderId);
		Order updateOrder = null;
		if (orderFromDb.isPresent()) {
			Order orderFromRepo = orderFromDb.get();
			if (order.getDeliveryDate() != null) {
				orderFromRepo.setDeliveryDate(order.getDeliveryDate());
			}
			orderFromRepo.setCart(order.getCart());
			return orderRepository.save(orderFromRepo);
		} else {
			log.info("Order List,service; update Order with orderId not found");
			throw new OrderNotFoundException("SERVICE.Order-Service_NOT_FOUND");
		}
	}

	@Override
	public Order findById(String orderId) {

		log.info("Order List,service; fetch Orders by id");
		Optional<Order> orderFromRepo = orderRepository.findByOrderId(orderId);
		if (orderFromRepo.isPresent()) {
			return orderFromRepo.get();
		} else {
			log.info("Order List, service; fetch Orders not found for given id");

			throw new OrderNotFoundException("SERVICE.Order-Service_NOT_FOUND");
		}
	}

	@Override
	public void deleteById(String orderId) {
		Optional<Order> order = orderRepository.findByOrderId(orderId);
		if (order.isPresent()) {
			log.info("Void,service; delete Order by id");
			orderRepository.deleteByOrderId(orderId);
		} else {
			log.info("Void,service; id not found for delete");
			throw new OrderNotFoundException("SERVICE.Order-Service_NOT_FOUND");
		}
	}
}
