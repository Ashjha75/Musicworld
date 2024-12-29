package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.addressRequest;
import com.example.springcommerce.entity.addressEntity;
import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.repository.addressRepo;
import com.example.springcommerce.repository.userRepo;
import com.example.springcommerce.service.addressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class addressServiceImpl implements addressService {

    private final addressRepo addressRepository;
    private final ModelMapper modelMapper;
    private final userRepo userRepository;


    @Autowired
    public  addressServiceImpl(addressRepo addressRepository, ModelMapper modelMapper, userRepo userRepo) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepo;
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
    public List<addressRequest> getUserAddresses(userEntity user) {
        List<addressEntity> addressList = user.getAddress();
        return addressList.stream().map(address -> modelMapper.map(address, addressRequest.class)).toList();
    }

    @Override
    public addressRequest updateAddress(Long id, addressRequest addressRequest) {
        addressEntity addressEntity = modelMapper.map(addressRequest, addressEntity.class);

        addressEntity savedAddress = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        savedAddress.setCity(addressEntity.getCity());
        savedAddress.setCountry(addressEntity.getCountry());
        savedAddress.setState(addressEntity.getState());
        savedAddress.setStreet(addressEntity.getStreet());
        savedAddress.setPinCode(addressEntity.getPinCode());
        savedAddress.setBuildingName(addressEntity.getBuildingName());

        addressEntity updatedAddress = addressRepository.save(savedAddress);

        userEntity userEntity = savedAddress.getUser();
        userEntity.getAddress().removeIf(address -> address.getAddressId().equals(id));
        userEntity.getAddress().add(updatedAddress);

        userRepository.save(userEntity);

        return modelMapper.map(updatedAddress, addressRequest.class);
    }


}
