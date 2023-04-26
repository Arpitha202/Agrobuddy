package com.stackroute.productservice.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.stackroute.productservice.exception.IdNotFoundException;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.model.ProductSell;
import com.stackroute.productservice.repository.ProductRepository;
import com.stackroute.productservice.service.ProductSellService;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ProductSellServiceImpl implements ProductSellService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Object addProduct(ProductSell productSell) {
		productSell.setProductId(productSell.getProductId());
		productSell.setProductName(productSell.getProductName());
		productSell.setSellerEmail(productSell.getSellerEmail());
		productSell.setProductPrice(productSell.getProductPrice());
		productSell.setDescription(productSell.getDescription());
		ProductSell savedProduct = productRepository.save(productSell);
		return savedProduct;
	}

	@Override

	public Object addProductImage(String productId, MultipartFile multipartFile) {

		boolean x = productRepository.existsByProductId(productId);
		ProductSell productfromRepo = null;
		if (!x) {
			try {
				throw new ProductNotFoundException("product not found");
			} catch (ProductNotFoundException e) {
				throw new RuntimeException(e);
			}
		} else {
			// productfromRepo = productRepository.findByProductName(productName);
			productfromRepo = productRepository.findById(productId).get();
			try {
				log.info("In Service Class for Add Method for adding Image");
				productfromRepo.setProductImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
				productRepository.save(productfromRepo);
				log.info("In Service Class for Add Method for adding Image sucessfull");

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
		return productfromRepo;
	}

	//		public ProductSell saveProduct(ProductSell productSell,MultipartFile file) throws IOException {
//		productSell.setProductImage(new Binary(BsonBinarySubType.BINARY,file.getBytes()));
//		productSell.setProductName(productSell.getProductName());
//		productSell.setProductPrice(productSell.getProductPrice());
//		productSell.setDescription(productSell.getDescription());
//		productSell.setSellerEmail(productSell.getSellerEmail());
//		return productRepository.save(productSell);
//	}
	@Override
	public List<ProductSell> getAllProduct() {
		return productRepository.findAll();
	}

//	@Override
//	public List<ProductSell> getProductBySellerEmail(String sellerEmail) {
//		return productRepository.findBySellerEmail(sellerEmail);
//	}

	@Override
	public List<ProductSell> getProductBySellerEmail(String sellerEmail) {
		return (List<ProductSell>) productRepository.findBySellerEmail(sellerEmail);
	}

	@Override
	public ProductSell getProductById(String productId) {
		Optional<ProductSell> productFromRepo = productRepository.findById(productId);
		if (productFromRepo.isPresent()) {
			return productFromRepo.get();
		} else {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}
	}

	@Override
	public String deleteProductById(String productId) {
		if (!productRepository.existsById(productId)) {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}
		productRepository.deleteById(productId);
		return "Product deleted successfully";
	}

	@Override
	public ProductSell updateProductById(ProductSell productSell) {
		Optional<ProductSell> productDb = productRepository.findById(productSell.getProductId());
		ProductSell updatedProduct = null;
		if (productDb.isPresent()) {
			ProductSell productFromRepo = productDb.get();
			productFromRepo.setProductName(productSell.getProductName());
			productFromRepo.setSellerEmail(productSell.getSellerEmail());
			productFromRepo.setProductPrice(productSell.getProductPrice());
			productFromRepo.setDescription(productSell.getDescription());
			updatedProduct = productRepository.save(productFromRepo);
		} else {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}

		return updatedProduct;
	}




}
