package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderItemRequest;
import com.example.springcommerce.DTO.Request.orderRequest;
import com.example.springcommerce.entity.*;
import com.example.springcommerce.exception.ApiException;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.*;
import com.example.springcommerce.service.cartService;
import com.example.springcommerce.service.orderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class orderServiceImpl implements orderService {
    private final cartRepo cartRepository;
    private final addressRepo addressRepository;
    private final orderItemRepo orderItemRepository;
    private final orderRepo orderRepository;
    private final paymentRepo paymentRepository;
    private final ModelMapper modelMapper;
    private final cartService cartService;
    private final productRepo productRepository;

    @Autowired
    public orderServiceImpl(cartRepo cartRepository, addressRepo addressRepository, orderItemRepo orderItemRepository, orderRepo orderRepository, ModelMapper modelMapper, cartService cartService, paymentRepo paymentRepository, productRepo productRepository) {
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.cartService = cartService;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public orderRequest placeOrder(String emailId, String paymentMethod, orderBodyRequest orderRequestBody) {

        cartEntity cart = cartRepository.findCartByEmail(emailId);
        if (cart == null) {
            throw new ResourceNotFound("Cart", "email", emailId);
        }

        addressEntity address = addressRepository.findById(orderRequestBody.getAddressId()).orElseThrow(() -> new ResourceNotFound( orderRequestBody.getAddressId(),"Address", "id"));

        orderEntity order = new orderEntity();
        order.setEmail(emailId);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted !");
        order.setAddress(address);

        paymentEntity payment = new paymentEntity(paymentMethod, orderRequestBody.getPaymentGatewayPaymentId(),orderRequestBody.getPaymentGatewayPaymentStatus(),orderRequestBody.getPaymentGatewayResponseMessage(),orderRequestBody.getPaymentGatewayName());
        payment.setOrder(order);
        payment = paymentRepository.save(payment);
        order.setPayment(payment);

        orderEntity savedOrder = orderRepository.save(order);
// Transfer the cart items to order items
        List<cartItemsEntity> cartItems = cart.getCartItems();
        if(cartItems.isEmpty()){
            throw new ApiException("Cart is Empty");
        }

        List<orderItemEntity> orderItems = new ArrayList<>();
        for (cartItemsEntity cartItem : cartItems) {
            orderItemEntity orderItem = new orderItemEntity();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItem.setOrderProductPrice(cartItem.getProductPrice());
            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);

        cart.getCartItems().forEach(item -> {
            int quantity = item.getQuantity();
            productEntity product = item.getProduct();

//            Reduce the quantity of the product in the product table
            product.setQuantity(product.getQuantity() - quantity);

            productRepository.save(product);

//            Remove the product from the cart
            cartService.deleteProductFromCart(item.getProduct().getProductId());
        });
        cart.setTotalPrice(0);
        cartRepository.save(cart);


        orderRequest orderDto= modelMapper.map(savedOrder, orderRequest.class);
        orderItems.forEach(orderItem -> orderDto.getOrderItems().add(modelMapper.map(orderItem, orderItemRequest.class)));

        orderDto.setAddressId(address.getAddressId());
        return orderDto;
    }

}
