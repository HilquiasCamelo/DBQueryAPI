package com.hilquiascamelo.dbqueryapi.service.util;

import com.hilquiascamelo.dbqueryapi.entity.Users;
import com.hilquiascamelo.dbqueryapi.entity.enums.Perfil;
import com.hilquiascamelo.dbqueryapi.repository.UserRepository;
import com.hilquiascamelo.dbqueryapi.service.UserRepositoryImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class SeedDbService {
    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UserRepository userRepository;

    public void seedDatabase() {
        Users user = new Users(1, "Hilquias Camelo", pe.encode("123"), "hilquiaswpc11@outlook.com", Perfil.toEnum(1), true);
        List <Users> userList = new ArrayList <>();
        userList.add(user);
        userRepository.saveAll(userList);
    }
}
