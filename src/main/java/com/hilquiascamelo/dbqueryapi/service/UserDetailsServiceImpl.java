package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.entity.Users;
import com.hilquiascamelo.dbqueryapi.repository.UserRepository;
import com.hilquiascamelo.dbqueryapi.security.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername ( String email ) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email );

        if(user == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserValidator(user.getIdUsers( ), user.getEmail( ), user.getPassword( ), user.getProfile( ));
    }

}