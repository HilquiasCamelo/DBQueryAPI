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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("Usuário não encontrado para o e-mail: " + email);
            throw new UsernameNotFoundException(email);
        }

        System.out.println("Usuário encontrado: " + user.getName() + " (" + user.getEmail() + ")");

        return new UserValidator(user.getIdUsers(), user.getName(), user.getEmail(), user.getPassword(), user.getProfile());
    }


}