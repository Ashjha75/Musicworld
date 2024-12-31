package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.service.addressService;
import com.example.springcommerce.serviceImplementation.addressServiceImpl;
import com.example.springcommerce.utils.utilityGroup.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@Tag(name = "Address API", description = "Endpoints for managing address")
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
        userEntity user = authUtil.loggedInUser();
        addressRequest address = addressService.createAddress(addressRequest, user);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-address")
    public ResponseEntity<List<addressRequest>> getAllAddress() {
        List<addressRequest> address = addressService.getAllAddress();
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/get-address/{id}")
    public ResponseEntity<addressRequest> getAddressById(@PathVariable Long id) {
        addressRequest address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/get-user-addresses")
    public ResponseEntity<List<addressRequest>> getUserAddresses() {
        userEntity user = authUtil.loggedInUser();
        List<addressRequest> address = addressService.getUserAddresses(user);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/update-address/{id}")
    public ResponseEntity<addressRequest> updateAddress(@PathVariable Long id, @Valid @RequestBody addressRequest addressRequest) {
        addressRequest address = addressService.updateAddress(id, addressRequest);
        return new ResponseEntity<addressRequest>(address, HttpStatus.OK);
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }
}
