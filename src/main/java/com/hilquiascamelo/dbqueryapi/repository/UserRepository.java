package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository <Users, Integer> {

    @Transactional(readOnly=true)
    Users findByEmail(String email);
}


