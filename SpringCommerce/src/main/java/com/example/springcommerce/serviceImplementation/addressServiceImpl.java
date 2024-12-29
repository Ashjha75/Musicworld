package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.addressEntity;
import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.repository.addressRepo;
import com.example.springcommerce.service.addressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class addressServiceImpl implements addressService {

    private final addressRepo addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public  addressServiceImpl(addressRepo addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public addressRequest createAddress(addressRequest addressRequest, userEntity user) {
        addressEntity addressEntity = modelMapper.map(addressRequest, addressEntity.class);

        List<addressEntity> addressList = user.getAddress();
        addressList.add(addressEntity);
        user.setAddress(addressList);

        addressEntity.setUser(user);
        addressEntity savedAddress = addressRepository.save(addressEntity);

        return modelMapper.map(savedAddress, addressRequest.class);
    }

    @Override
    public List<addressRequest> getAllAddress() {
        List<addressEntity> addressList = addressRepository.findAll();
        return addressList.stream().map(address -> modelMapper.map(address, addressRequest.class)).toList();
    }

    @Override
    public addressRequest getAddressById(Long id) {
        addressEntity address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        return modelMapper.map(address, addressRequest.class);
    }

    @Override
    public List<addressRequest> getUserAddresses(String email) {
        List<addressEntity> addressList = addressRepository.findByUserEmail(email);
        return addressList.stream().map(address -> modelMapper.map(address, addressRequest.class)).toList();
    }


}
