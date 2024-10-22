package com.example.springcommerce.utils.Security.Service;

import com.example.springcommerce.entity.userEntity;
import com.example.springcommerce.repository.userRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for loading user-specific data.
 * Implements the UserDetailsService interface provided by Spring Security.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    userRepo userRepository;

    @Autowired
    public UserDetailsServiceImpl(userRepo userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user details by username.
     *
     * @param username the username identifying the user whose data is required.
     * @return UserDetails a fully populated user record (never null)
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}