package com.stackroute.productservice.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import com.stackroute.productservice.exception.IdNotFoundException;
import com.stackroute.productservice.model.ProductRent;
import com.stackroute.productservice.repository.ProductRentRepository;
import com.stackroute.productservice.service.ProductRentService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ProductRentServiceImpl implements ProductRentService {

	@Autowired
	ProductRentRepository repository;
	@Override
	public ProductRent addProduct(ProductRent productRentDto) {

		ProductRent productRent1=new ProductRent();
		productRent1.setRentProductImage1(productRentDto.getRentProductImage1());
		productRent1.setRentId(productRentDto.getRentId());
		productRent1.setRentProductName(productRentDto.getRentProductName());
		productRent1.setRenterEmail(productRentDto.getRenterEmail());
		productRent1.setRentProductPrice_PerDay(productRentDto.getRentProductPrice_PerDay());
		productRent1.setProductDescription(productRentDto.getProductDescription());

		ProductRent savedProduct=repository.save(productRent1);
		return savedProduct;
	}

	@Override
	public List<ProductRent> getAllProduct() {
		return repository.findAll();
	}

	@Override
	public ProductRent getProductById(String rentId) {

		Optional<ProductRent> productFromRepo=repository.findById(rentId);
		if(productFromRepo.isPresent()) {
			return productFromRepo.get();
		}
		else {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}
	}

	@Override
	public String deleteProductById(String rentId) {
		if(!repository.existsById(rentId)) {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}
		repository.deleteById(rentId);
		return "Product deleted successfully";
		
	}

	@Override
	public ProductRent updateProductById(ProductRent productRentDto) {
		Optional<ProductRent> productDb=repository.findById(productRentDto.getRentId());
		ProductRent updatedProduct=null;
		if(productDb.isPresent()) {
			ProductRent	productFromRepo=productDb.get();
			productFromRepo.setRentProductImage1(productRentDto.getRentProductImage1());
			productFromRepo.setRentProductName(productRentDto.getRentProductName());
			productFromRepo.setRenterEmail(productRentDto.getRenterEmail());
			productFromRepo.setRentProductPrice_PerDay(productRentDto.getRentProductPrice_PerDay());
			productFromRepo.setProductDescription(productRentDto.getProductDescription());

			updatedProduct =repository.save(productFromRepo);
		}else {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}
		return updatedProduct;
	}

}
