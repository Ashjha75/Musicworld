package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.cartRequest;
import com.example.springcommerce.entity.cartEntity;
import com.example.springcommerce.repository.cartRepo;
import com.example.springcommerce.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cartServiceImpl implements cartService {
    @Autowired
    private  cartRepo cartRepo;
    @Autowired
    private AuthUtil authutils;

    @Override
    public cartRequest addProductTocart(Long productId, Integer quantity) {
        return null;
    }
}


private cartEntity createCart(){


    cartEntity userCart= cartRepo.findCartByEmail(authutils.loggedInEmail());

    if(userCart!=null){
        return userCart;
    }

    cartEntity cart = new cartEntity();
    cart.setTotalPrice(0.00);
    cart.setUser(authutils.loggedInUser());
    return cartRepo.save(cart);
}