package com.pbl.filesharing.FileSharing.service;

import com.pbl.filesharing.FileSharing.entity.CustomUserDetails;
import com.pbl.filesharing.FileSharing.entity.User;
import com.pbl.filesharing.FileSharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Beatrice V.
 * @created 24.11.2020 - 18:38
 * @project FileSharing
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
