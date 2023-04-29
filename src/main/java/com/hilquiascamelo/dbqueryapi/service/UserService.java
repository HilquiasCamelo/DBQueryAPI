package com.hilquiascamelo.dbqueryapi.service;


import com.hilquiascamelo.dbqueryapi.entity.Users;
import com.hilquiascamelo.dbqueryapi.entity.dto.UserNewDTO;
import com.hilquiascamelo.dbqueryapi.entity.enums.Perfil;
import com.hilquiascamelo.dbqueryapi.exceptions.DataIntegrityException;
import com.hilquiascamelo.dbqueryapi.interfaces.UserServiceInterface;
import com.hilquiascamelo.dbqueryapi.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Override
    public Users find(Integer id) {
        Optional<Users> user = repo.findById(id);
        return user.orElseThrow( );
    }

    @Override
    public void delete ( Integer id ) {
        find( id );
        try{
            repo.deleteById( id );
        }
        catch( DataIntegrityViolationException e ) {
            throw new DataIntegrityException( "Não é possível excluir porque há usuários relacionados" );
        }
    }


    @Override
    public Page < Users > findAll ( Pageable pageable ) {
        return repo.findAll( pageable );
    }

    @Override
    public Users insert ( Users obj ) {
        return repo.save( obj );
    }

    @Override
    public Users fromDTO(UserNewDTO objDto) {


        if (pe == null) {
            throw new IllegalStateException("Password encoder not initialized");
        }

        return new Users( null,
                objDto.getNome(),
                pe.encode(objDto.getPassword()),
                objDto.getEmail(),   Perfil.toEnum(objDto.getTipo()),true);
    }


    @Override
    public Users update ( Users obj ) {
        Users newObj = find( obj.getIdUsers( ) );
        updateData( newObj , obj );
        return repo.save( newObj );
    }

    private void updateData (
            Users newObj ,
            Users obj
                            ) {
        newObj.setName( obj.getName( ) );
        newObj.setEmail( obj.getEmail( ) );
        newObj.setPassword( pe.encode( obj.getPassword( ) ) );
    }
}
