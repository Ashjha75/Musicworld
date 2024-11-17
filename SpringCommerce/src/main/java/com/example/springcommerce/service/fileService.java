package com.example.springcommerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface fileService {

    String uploadImage(String path, MultipartFile file) throws IOException;
}
