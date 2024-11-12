
package com.example.springcommerce.utils.Security.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String jwtToken;

    private String userName;
    private List<String> roles;

    public UserInfoResponse(Long id, String jwtToken, String userName, List<String> roles) {
        this.id = id;
        this.userName = userName;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

    public UserInfoResponse(Long id, String userName, List<String> roles) {
        this.id = id;
        this.userName = userName;
        this.roles = roles;
    }

}
