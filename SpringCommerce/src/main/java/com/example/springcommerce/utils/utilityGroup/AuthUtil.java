package com.example.springcommerce.utils.utilityGroup;


import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {


    private final com.example.springcommerce.repository.userRepo userRepo;

    @Autowired
    public AuthUtil(userRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String loggedInEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntity user = userRepo.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getEmail();
    }

   public userEntity loggedInUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    userEntity user = userRepo.findByUsername(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return user;
}


}
