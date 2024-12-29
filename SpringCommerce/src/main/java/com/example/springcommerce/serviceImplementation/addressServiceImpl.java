package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.service.addressService;
import org.springframework.stereotype.Service;

@Service
public class addressServiceImpl implements addressService {

    @Override
    public addressRequest createAddress(addressRequest addressRequest, userEntity user) {
        return null;
    }
}
