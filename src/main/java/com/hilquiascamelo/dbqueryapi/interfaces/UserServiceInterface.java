package com.hilquiascamelo.dbqueryapi.interfaces;

import com.hilquiascamelo.dbqueryapi.entity.Users;
import com.hilquiascamelo.dbqueryapi.entity.dto.UserNewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserServiceInterface {
Users find( Integer id);

void delete(Integer id);

Page <Users> findAll( Pageable pageable);

Users insert(Users obj);

Users fromDTO( UserNewDTO objDto);

Users update(Users obj);
}