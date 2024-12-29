package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.userEntity;


public interface addressService {
    addressRequest createAddress(addressRequest addressRequest, userEntity user);
}
