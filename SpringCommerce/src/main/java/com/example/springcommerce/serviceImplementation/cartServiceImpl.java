package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.cartRequest;
import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.entity.cartEntity;
import com.example.springcommerce.entity.cartItemsEntity;
import com.example.springcommerce.entity.productEntity;
import com.example.springcommerce.exception.ApiException;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.cartItemRepo;
import com.example.springcommerce.repository.cartRepo;
import com.example.springcommerce.repository.productRepo;
import com.example.springcommerce.service.cartService;
import com.example.springcommerce.utils.utilityGroup.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class cartServiceImpl implements cartService {
    @Autowired
    private cartRepo cartRepo;
    @Autowired
    private AuthUtil authutils;
    @Autowired
    private productRepo productRepository;
    @Autowired
    private cartItemRepo cartItemsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public cartRequest addProductTocart(Long productId, Integer quantity) {
        cartEntity cart = createCart();

        productEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound(productId, "ID", "Product"));

        cartItemsEntity cartItem = cartItemsRepository.findCartItemByProductIDAndCartId(cart.getCartId(), productId);

        if (cartItem != null) {
            throw new ApiException("Product " + product.getProductName() + " already exists in cart");
        }
        if (product.getQuantity() == 0) {
            throw new ApiException("Product " + product.getProductName() + " is out of stock");
        }
        if (quantity > product.getQuantity()) {
            throw new ApiException("Product " + product.getProductName() + " quantity is more than available stock");
        }

        cartItemsEntity newCartItem = new cartItemsEntity();

        newCartItem.setProduct(product);
        newCartItem.setQuantity(quantity);
        newCartItem.setCart(cart);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getPrice());

        cartItemsRepository.save(newCartItem);

        product.setQuantity(product.getQuantity() - quantity);

        cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));
        cartRepo.save(cart);

        cartRequest cartRequest = modelMapper.map(cart, cartRequest.class);

        List<cartItemsEntity> cartItemsList = cart.getCartItems();

        Stream<productRequest> productRequestStream = cartItemsList.stream().map(cartItemsEntity -> {
            productRequest productRequest = modelMapper.map(cartItemsEntity.getProduct(), productRequest.class);
            productRequest.setQuantity(cartItemsEntity.getQuantity());
            return productRequest;
        });

        cartRequest.setCartItems(productRequestStream.toList());
        return cartRequest;
    }

    private cartEntity createCart() {
        cartEntity userCart = cartRepo.findCartByEmail(authutils.loggedInEmail());

        if (userCart != null) {
            return userCart;
        }

        cartEntity cart = new cartEntity();
        cart.setTotalPrice(0.00);
        cart.setUser(authutils.loggedInUser());
        return cartRepo.save(cart);
    }

    @Override
    public List<cartRequest> getAllCarts() {
       List<cartEntity> cartList = cartRepo.findAll();
       if (cartList.isEmpty()) {
           throw new ApiException("No carts found");
       }

       List<cartRequest> cartRequestList = cartList.stream().map(cartEntity -> {
           cartRequest cartRequest = modelMapper.map(cartEntity, cartRequest.class);
           List<productRequest> productRequestList = cartEntity.getCartItems().stream().map(product ->
                   modelMapper.map(product.getProduct(), productRequest.class)).toList();
              cartRequest.setCartItems(productRequestList);
                return cartRequest;
         }).toList();
            return cartRequestList;
       }


}