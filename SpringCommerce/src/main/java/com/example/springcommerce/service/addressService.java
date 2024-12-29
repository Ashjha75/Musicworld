package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.userEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;


public interface addressService {
    addressRequest createAddress(addressRequest addressRequest, userEntity user);

    List<addressRequest> getAllAddress();

    addressRequest getAddressById(Long id);

    List<addressRequest> getUserAddresses(@NotBlank @Size(min = 6, max = 100) @Email(message = "must be a well-formed email address") String email);
}
