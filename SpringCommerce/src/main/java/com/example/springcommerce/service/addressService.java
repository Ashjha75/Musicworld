package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.userEntity;

import java.util.List;


public interface addressService {
    addressRequest createAddress(addressRequest addressRequest, userEntity user);

    List<addressRequest> getAllAddress();
}
