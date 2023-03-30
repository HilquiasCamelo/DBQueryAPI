package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoNotFoundException;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoReferencedException;
import com.hilquiascamelo.dbqueryapi.service.CargoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger( CargoService.class );
    public int deleteCargo(Integer id) {
        try {
            if (existsById(id)) {
                if (isCargoReferenced(id)) {
                    throw new CargoReferencedException("Não é possível excluir o cargo porque ele é referenciado em outras tabelas.");
                }
                String sql = "DELETE FROM cargo WHERE id_cargo = :id";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("id", Long.valueOf(id)); // Converter para Long
                int rowsDeleted = query.executeUpdate();
                return rowsDeleted;
            } else {
                throw new CargoNotFoundException("ID do cargo não encontrado: " , id);
            }
        } catch (CargoNotFoundException e) {
            LOGGER.error("Erro ao excluir cargo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (CargoReferencedException e) {
            LOGGER.error("Erro ao excluir cargo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Erro ao excluir cargo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, " - Ocorreu um erro ao excluir o cargo");
        }
    }


    private boolean isCargoReferenced(Integer id) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(*) FROM Pessoa  WHERE oid_cargo_quadro_sgp = :id", Long.class);
        query.setParameter("id", id);

        Long count = query.getSingleResult();
        boolean exists = count > 0;
        LOGGER.debug("isCargoReferenced: id={}, exists={}, count={}, query={}", id, exists, count, query);
        return exists;
    }

    public boolean existsById(Integer id) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(*) FROM Cargo WHERE id_cargo = :id", Long.class);
        query.setParameter("id", id.longValue());

        Long count = query.getSingleResult();
        boolean exists = count > 0;
        LOGGER.debug("existsById: id={}, exists={}, count={}, query={}", id, exists, count, query);
        return exists;
    }
}

