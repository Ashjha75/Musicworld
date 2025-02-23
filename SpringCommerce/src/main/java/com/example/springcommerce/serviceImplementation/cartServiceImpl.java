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
import jakarta.transaction.Transactional;
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

    @Transactional
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
                    {
                        productRequest productRequest = modelMapper.map(product.getProduct(), productRequest.class);
                        productRequest.setQuantity(productRequest.getQuantity());
                        return productRequest;
                    }
            ).toList();
            cartRequest.setCartItems(productRequestList);
            return cartRequest;
        }).toList();
        return cartRequestList;
    }

    @Override
    public cartRequest getCart(String email, Long cartId) {
        cartEntity cart = cartRepo.findCartByEmailAndCartId(email, cartId);
        if (cart == null) {
            throw new ApiException("Cart not found");
        }
        cartRequest cartRequest = modelMapper.map(cart, cartRequest.class);
        cart.getCartItems().forEach(c -> c.getProduct().setQuantity(c.getProduct().getQuantity() + c.getQuantity()));
        List<productRequest> productRequestList = cart.getCartItems().stream().map(product ->
                modelMapper.map(product.getProduct(), productRequest.class)).toList();

        return cartRequest;
    }

    @Transactional
    @Override
    public cartRequest updateProductQuantityInCart(Long productId, Integer quantity) {

        String email = authutils.loggedInEmail();
        cartEntity cart = cartRepo.findCartByEmail(email);
        Long cartId = cart.getCartId();

        cartEntity cartEntity = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFound(cartId, "ID", "Cart"));

        productEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound(productId, "ID", "Product"));

        if (product.getQuantity() < 1) {
            throw new ApiException("Product " + product.getProductName() + " is out of stock");
        }
        if (product.getQuantity() < quantity) {
            throw new ApiException("Product " + product.getProductName() + " quantity is more than available stock");
        }

        cartItemsEntity cartItem = cartItemsRepository.findCartItemByProductIDAndCartId(cartId, productId);

        if (cartItem == null && quantity > 0) {
            cartRequest addedProduct = addProductTocart(productId, quantity);
            return addedProduct;
        } else if (cartItem == null && quantity < 0) {
            throw new ApiException("Product does not exist in cart");
        }

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setProduct(product);
        cartItem.setCart(cartEntity);
        cartItem.setDiscount(product.getDiscount());
        cartItem.setProductPrice(product.getPrice());
        cartEntity.setTotalPrice(cartEntity.getTotalPrice() + (product.getSpecialPrice() * quantity));
        cartItemsRepository.save(cartItem);
        cartItemsEntity updatedCartItem = cartItemsRepository.save(cartItem);
        if (updatedCartItem.getQuantity() <= 0) {
            deleteProductFromCart(productId);
        }

        cartRequest cartRequest = modelMapper.map(cartEntity, cartRequest.class);
        List<cartItemsEntity> cartItemsList = cartEntity.getCartItems();

        Stream<productRequest> productRequestStream = cartItemsList.stream().map(cartItemsEntity -> {
            productRequest productRequest = modelMapper.map(cartItemsEntity.getProduct(), productRequest.class);
            productRequest.setQuantity(cartItemsEntity.getQuantity());
            return productRequest;
        });

        cartRequest.setCartItems(productRequestStream.toList());
        return cartRequest;
    }

    @Transactional
    @Override
    public String deleteProductFromCart(Long productId) {

        String email = authutils.loggedInEmail();
        cartEntity cart = cartRepo.findCartByEmail(email);
        Long cartId = cart.getCartId();

        cartEntity cartEntity = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFound(cartId, "ID", "Cart"));


        cartItemsEntity cartItem = cartItemsRepository.findCartItemByProductIDAndCartId(cartId, productId);
        if (cartItem == null) {
            throw new ApiException("Product does not exist in cart");
        }
        productEntity product = cartItem.getProduct();

        cartEntity.setTotalPrice(cartEntity.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity()));

        product.setQuantity(product.getQuantity() + cartItem.getQuantity());

        cartItemsRepository.deleteCartItemByProductIDAndCartId(cartId, productId);

        return "Product " + cartItem.getProduct().getProductName() + " deleted from cart";

    }

    @Override
    public void updateProductInCart(Long cartId, Long productId) {
        cartEntity cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFound(cartId, "ID", "Cart"));
        productEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound(productId, "ID", "Product"));

        cartItemsEntity cartItem = cartItemsRepository.findCartItemByProductIDAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ApiException("Product does not exist in cart");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());
        cartItem.setProductPrice(product.getSpecialPrice());
        cart.setTotalPrice(cartPrice + (product.getSpecialPrice() * cartItem.getQuantity()));
        cartItem = cartItemsRepository.save(cartItem);

    }

}