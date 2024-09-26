package com.example.springcommerce.DTO.Response;


import com.example.springcommerce.DTO.Request.categoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    List<categoryRequest> response;
}
