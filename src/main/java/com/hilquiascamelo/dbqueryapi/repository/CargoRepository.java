package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoNotFoundException;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoReferencedException;
import com.hilquiascamelo.dbqueryapi.service.CargoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class CargoRepository {

    @PersistenceContext private EntityManager entityManager;
    private int rowsDeleted;

    public List < Cargo > saveAll ( List < Cargo > cargos ) {
        String sql = "INSERT INTO cargo (ativo, descricao, ordem, sigla) VALUES (:ativo, :descricao, :ordem, :sigla)";

        Query queryInsert = entityManager.createNativeQuery( sql );

        for( Cargo cargo : cargos ) {
            queryInsert.setParameter( "ativo" , cargo.isAtivo( ) );
            queryInsert.setParameter( "descricao" , cargo.getDescricao( ) );
            queryInsert.setParameter( "ordem" , cargo.getOrdem( ) );
            queryInsert.setParameter( "sigla" , cargo.getSigla( ) );
            queryInsert.executeUpdate( );
        }

        List < Cargo >
                sortedCargos =
                entityManager.createNativeQuery( "SELECT id_cargo, ativo, descricao, ordem, sigla" +
                                                 " FROM cargo ORDER BY id_cargo ASC" , Cargo.class )
                        .getResultList( );

        return sortedCargos;
    }

    public List < Cargo > getCargoList ( ) {

        List < Cargo >
                sortedCargos =
                entityManager.createNativeQuery( "SELECT id_cargo, ativo, descricao, ordem, sigla" +
                                                 " FROM cargo ORDER BY id_cargo ASC" , Cargo.class )
                        .getResultList( );

        return sortedCargos;

    }

    public Cargo putCargo ( Cargo cargo , Integer id ) {
        String
                sql =
                "UPDATE cargo SET ativo=:ativo, descricao=:descricao, " +
                "ordem=:ordem, sigla=:sigla WHERE id_cargo = :id";

        Query queryUpdate = entityManager.createNativeQuery( sql );
        queryUpdate.setParameter( "ativo" , cargo.isAtivo( ) );
        queryUpdate.setParameter( "descricao" , cargo.getDescricao( ) );
        queryUpdate.setParameter( "ordem" , cargo.getOrdem( ) );
        queryUpdate.setParameter( "sigla" , cargo.getSigla( ) );
        queryUpdate.setParameter( "id" , id );
        queryUpdate.executeUpdate( );

        Cargo
                querySelect =
                ( Cargo ) entityManager.createNativeQuery( "SELECT id_cargo, ativo, descricao, ordem, " +
                                                           "sigla FROM cargo WHERE id_cargo=:id" , Cargo.class )
                        .setParameter( "id" , id )
                        .getSingleResult( );

        return querySelect;
    }
    public Integer deleteCargo(Integer id) {
        String sql = "DELETE FROM cargo WHERE id_cargo = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
