package com.stackroute.cartservice.serviceImpl;

import com.stackroute.cartservice.exception.ProductNotFoundException;
import com.stackroute.cartservice.modal.Cart;
import com.stackroute.cartservice.modal.Product;
import com.stackroute.cartservice.modal.ProductDto;
import com.stackroute.cartservice.modal.UserDao;
import com.stackroute.cartservice.modal.UserDto;
import com.stackroute.cartservice.repository.CartRepository;
import com.stackroute.cartservice.repository.ProductRepository;
import com.stackroute.cartservice.repository.UserRepository;
import com.stackroute.cartservice.service.ProductService;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	@SuppressWarnings({ "rawtypes", "unused" })
	private ResponseEntity response;
	private final CartRepository repository;

	@Autowired
	public ProductServiceImpl(CartRepository repository) {
		this.repository = repository;
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productSellRepo;

	@Override
	public Cart saveProduct(Cart newCart) throws Exception {
		System.out.println(newCart.getBuyerEmail());
		System.out.println(newCart.getProductQuantity());
		System.out.println(newCart.getProductList().toString());
		Optional<Cart> existingCart = repository.findByBuyerEmail(newCart.getBuyerEmail());
		
		if (existingCart.isPresent()) {
			updateProductQuantityInLatestCart(existingCart.get(), newCart);
			repository.deleteByBuyerEmail(newCart.getBuyerEmail());
		}
		return repository.save(newCart);
	}

	private void updateProductQuantityInLatestCart(Cart existingCart, Cart newCart) {
		List<Product> duplicateItem = new ArrayList<>();
		for (Product existingItem : existingCart.getProductList()) {
			for (Product newCartItem : newCart.getProductList()) {
				if (newCartItem.getProductId().equalsIgnoreCase(existingItem.getProductId())) {
//					newCartItem.setProductQuantity(existingItem.getProductQuantity() + newCartItem.getProductQuantity());
					duplicateItem.add(existingItem);
				}
			}
		}
		existingCart.getProductList().removeAll(duplicateItem);
		newCart.getProductList().addAll(existingCart.getProductList());
		existingCart.getProductList().removeIf(product -> product != null);
	}

	@Override
	public Cart getProductById(String productId) throws ProductNotFoundException {

		return repository.findByBuyerEmail(productId).get();
	}

	@Override
	public List<Product> getAllProducts(String buyerEmail) {
		return repository.findByBuyerEmail(buyerEmail).get().getProductList();

	}

	@Override
	public Cart updateAProduct(String email, String productId, int qty) throws ProductNotFoundException {
		Cart existingCart = null;
		try {
			Optional<Cart> cart = repository.findByBuyerEmail(email);
			if (!cart.isPresent()) {
				throw new ProductNotFoundException();
			}
			existingCart = cart.get();
			for (Product product : existingCart.getProductList()) {
				if (product.getProductId().equalsIgnoreCase(productId)) {
//					product.setProductQuantity(qty);
				}
			}
			repository.deleteByBuyerEmail(email);
			repository.save(existingCart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existingCart;
	}

	@Override
	public boolean deleteAProduct(String email, String productId) throws ProductNotFoundException {
		try {
			Optional<Cart> cart = repository.findByBuyerEmail(email);
			if (!cart.isPresent()) {
				throw new ProductNotFoundException();
			}
			Cart existingCart = cart.get();
			existingCart.getProductList().removeIf(product -> product.getProductId().equalsIgnoreCase(productId));
			repository.deleteByBuyerEmail(email);
			repository.save(existingCart);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public List deleteAllProducts(String email) throws ProductNotFoundException {
		System.out.println("this is email" + email);
		repository.deleteByBuyerEmail(email);
		return new ArrayList<>();
	}

	@Override
	public Cart updateQuantity(Cart cart, String productId) throws Exception {
		return null;
	}

	@Override
	public Cart getCartByEmail(String emailId) {
		return null;
	}

	@Override
	public UserDao save(UserDto user) {
		UserDao dao = new UserDao();
		dao.setEmailId(user.getEmailId());
		return userRepository.save(dao);
	}

	@Override
	public Product addProduct(ProductDto product) {
		Product product2 = new Product();
		product2.setDescription(product.getDescription());
		product2.setProductId(product.getProductId());
		product2.setProductName(product.getProductName());
		product2.setProductPrice(product.getProductPrice());
		product2.setSellerEmail(product.getSellerEmail());
		product2.setProductImage(product.getProductImage());
		System.out.println(product);
		return productSellRepo.save(product2);
	}

}
