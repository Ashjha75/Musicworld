package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.service.fileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class fileServiceImpl implements fileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
//        filename of current / original file
        String originalFilename = file.getOriginalFilename();

//        Generate the unique file name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;
//        here we have used separator because we want that the file can be saved in any os because windows and unix have different way we have used inbuilt path separator of java

//        check if the path exists and create
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }

//        upload to Server
        Files.copy(file.getInputStream(), Paths.get(filePath));
//        return file name
        return fileName;
    }
}
