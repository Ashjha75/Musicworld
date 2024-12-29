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
    addressServiceImpl(addressRepo addressRepository, ModelMapper modelMapper) {
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
}
