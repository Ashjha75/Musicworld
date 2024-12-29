package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.serviceImplementation.addressServiceImpl;
import com.example.springcommerce.utils.utilityGroup.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springcommerce.service.addressService;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AuthUtil authUtil;
    private final addressService addressService;

    @Autowired
    public AddressController(AuthUtil authUtil, addressServiceImpl addressService) {
        this.authUtil = authUtil;
        this.addressService = addressService;
    }

    @PostMapping("/create-address")
    public ResponseEntity<addressRequest> createAddress(@Valid @RequestBody addressRequest addressRequest) {
        userEntity user =authUtil.loggedInUser();
        addressRequest address = addressService.createAddress(addressRequest, user);
        return  new ResponseEntity<>(address, HttpStatus.CREATED);
    }
}
