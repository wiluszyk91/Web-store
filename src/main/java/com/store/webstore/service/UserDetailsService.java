package com.store.webstore.service;

import com.store.webstore.model.CustomizeUserDetails;
import com.store.webstore.model.Role;
import com.store.webstore.model.User;
import com.store.webstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomizeUserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
                for(Role role : roles) {
            authorities.add((new SimpleGrantedAuthority(role.getName())));
        }
        CustomizeUserDetails customizeUserDetails = new CustomizeUserDetails();
                customizeUserDetails.setUser(user);
                customizeUserDetails.setAuthorities(authorities);

                return customizeUserDetails;
    }
}
