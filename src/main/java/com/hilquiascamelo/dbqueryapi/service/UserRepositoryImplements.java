package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.entity.Users;
import com.hilquiascamelo.dbqueryapi.exceptions.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserRepositoryImplements  {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Users findByEmail(String email) throws NotFoundException {
        String sql = "SELECT * FROM users WHERE email = :email";
        Query query = entityManager.createNativeQuery(sql, Users.class);
        query.setParameter("email", email);
        List<Users> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            throw new NotFoundException("User with email not found: ", email);
        }
    }


    public List < Users > saveAll ( List < Users > users ) {
        String sql = "INSERT INTO users (email, name, password, situation, type) VALUES (:email, :name, :password, :situation"
                     + ":type)";

        Query queryInsert = entityManager.createNativeQuery( sql );

        for( Users user : users ) {
            queryInsert.setParameter( "email" , user.getEmail( ) );
            queryInsert.setParameter( "name" , user.getName( ) );
            queryInsert.setParameter( "password" , user.getPassword( ) );
            queryInsert.setParameter( "situation" , user.getSituation( ) );
            queryInsert.setParameter( "type" , user.getType( ) );
            queryInsert.executeUpdate( );
        }

        List < Users >
                sortedCargos =
                entityManager.createNativeQuery( "SELECT id_users, email, name, password, situation, type" +
                                                 " FROM users ORDER BY id_users ASC" , Users.class )
                        .getResultList( );

        return sortedCargos;
    }
}
