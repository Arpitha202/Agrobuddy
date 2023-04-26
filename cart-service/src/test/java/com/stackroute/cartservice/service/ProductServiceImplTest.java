//package com.stackroute.cartservice.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.anyInt;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.stackroute.cartservice.exception.ProductNotFoundException;
//import com.stackroute.cartservice.modal.Cart;
//import com.stackroute.cartservice.modal.Product;
//import com.stackroute.cartservice.repository.CartRepository;
//
//import java.math.BigDecimal;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Disabled;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {ProductServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//class ProductServiceImplTest {
//    @MockBean
//    private CartRepository cartRepository;
//
//    @Autowired
//    private ProductServiceImpl productServiceImpl;
//
//    @Test
//    void testSaveProduct() throws Exception {
//        Cart cart = new Cart();
//        cart.setBuyerEmail("jane.doe@example.org");
//        cart.setProductList(new ArrayList<>());
//
//        Cart cart1 = new Cart();
//        cart1.setBuyerEmail("jane.doe@example.org");
//        cart1.setProductList(new ArrayList<>());
//        Optional<Cart> ofResult = Optional.of(cart1);
//        doNothing().when(cartRepository).deleteByBuyerEmail((String) any());
//        when(cartRepository.save((Cart) any())).thenReturn(cart);
//        when(cartRepository.findByBuyerEmail((String) any())).thenReturn(ofResult);
//
//        Cart cart2 = new Cart();
//        cart2.setBuyerEmail("jane.doe@example.org");
//        cart2.setProductList(new ArrayList<>());
//        assertSame(cart, productServiceImpl.saveProduct(cart2));
//        verify(cartRepository).save((Cart) any());
//        verify(cartRepository).findByBuyerEmail((String) any());
//        verify(cartRepository).deleteByBuyerEmail((String) any());
//        assertTrue(cart2.getProductList().isEmpty());
//    }
//
//    @Test
//    void testGetProductById() throws ProductNotFoundException {
//        Cart cart = new Cart();
//        cart.setBuyerEmail("jane.doe@example.org");
//        cart.setProductList(new ArrayList<>());
//        Optional<Cart> ofResult = Optional.of(cart);
//        when(cartRepository.findByBuyerEmail((String) any())).thenReturn(ofResult);
//        assertSame(cart, productServiceImpl.getProductById("42"));
//        verify(cartRepository).findByBuyerEmail((String) any());
//    }
//
//    @Test
//    void testGetAllProducts() {
//        Cart cart = new Cart();
//        cart.setBuyerEmail("jane.doe@example.org");
//        ArrayList<Product> productList = new ArrayList<>();
//        cart.setProductList(productList);
//        Optional<Cart> ofResult = Optional.of(cart);
//        when(cartRepository.findByBuyerEmail((String) any())).thenReturn(ofResult);
//        List<Product> actualAllProducts = productServiceImpl.getAllProducts("jane.doe@example.org");
//        assertSame(productList, actualAllProducts);
//        assertTrue(actualAllProducts.isEmpty());
//        verify(cartRepository).findByBuyerEmail((String) any());
//    }
//
//    @Test
//    void testUpdateAProduct() throws ProductNotFoundException {
//        Cart cart = new Cart();
//        cart.setBuyerEmail("jane.doe@example.org");
//        cart.setProductList(new ArrayList<>());
//        Optional<Cart> ofResult = Optional.of(cart);
//
//        Cart cart1 = new Cart();
//        cart1.setBuyerEmail("jane.doe@example.org");
//        cart1.setProductList(new ArrayList<>());
//        when(cartRepository.save((Cart) any())).thenReturn(cart1);
//        doNothing().when(cartRepository).deleteByBuyerEmail((String) any());
//        when(cartRepository.findByBuyerEmail((String) any())).thenReturn(ofResult);
//        assertSame(cart, productServiceImpl.updateAProduct("jane.doe@example.org", "42", 1));
//        verify(cartRepository).save((Cart) any());
//        verify(cartRepository).findByBuyerEmail((String) any());
//        verify(cartRepository).deleteByBuyerEmail((String) any());
//    }
//
//    @Test
//    void testDeleteAProduct() throws ProductNotFoundException {
//        Cart cart = new Cart();
//        cart.setBuyerEmail("jane.doe@example.org");
//        cart.setProductList(new ArrayList<>());
//        Optional<Cart> ofResult = Optional.of(cart);
//
//        Cart cart1 = new Cart();
//        cart1.setBuyerEmail("jane.doe@example.org");
//        cart1.setProductList(new ArrayList<>());
//        when(cartRepository.save((Cart) any())).thenReturn(cart1);
//        doNothing().when(cartRepository).deleteByBuyerEmail((String) any());
//        when(cartRepository.findByBuyerEmail((String) any())).thenReturn(ofResult);
//        assertTrue(productServiceImpl.deleteAProduct("jane.doe@example.org", "42"));
//        verify(cartRepository).save((Cart) any());
//        verify(cartRepository).findByBuyerEmail((String) any());
//        verify(cartRepository).deleteByBuyerEmail((String) any());
//    }
//
//    @Test
//    void testDeleteAllProducts() throws ProductNotFoundException {
//        doNothing().when(cartRepository).deleteByBuyerEmail((String) any());
//        assertTrue(productServiceImpl.deleteAllProducts("jane.doe@example.org").isEmpty());
//        verify(cartRepository).deleteByBuyerEmail((String) any());
//    }
//
//    @Test
//    void testUpdateQuantity() throws Exception {
//        Cart cart = new Cart();
//        cart.setBuyerEmail("jane.doe@example.org");
//        cart.setProductList(new ArrayList<>());
//        assertNull(productServiceImpl.updateQuantity(cart, "42"));
//    }
//
//    @Test
//    void testGetCartByEmail() {
//        assertNull(productServiceImpl.getCartByEmail("42"));
//    }
//}
//
