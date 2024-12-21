package com.example.springcommerce.DTO.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class categoryRequest {
    private Long categoryId;
    private String categoryName;
}
