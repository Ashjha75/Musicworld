package com.example.springcommerce.utils.Security;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String jwtToken;

    private String userName;
    private List<String> roles;

}
