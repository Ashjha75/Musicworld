package com.example.springcommerce.utils.Security.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoRequest {

    private String username;
    private String password;

}
