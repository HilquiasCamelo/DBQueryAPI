package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.dto.EscolaridadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CargoRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private int rowsDeleted;

    public List<Cargo> saveAll(List<Cargo> cargos) {
        String sql = "INSERT INTO cargo (ativo, descricao, ordem, sigla) VALUES (:ativo, :descricao, :ordem, :sigla)";

        Query queryInsert = entityManager.createNativeQuery(sql);

        for (Cargo cargo : cargos) {
            queryInsert.setParameter("ativo", cargo.isAtivo());
            queryInsert.setParameter("descricao", cargo.getDescricao());
            queryInsert.setParameter("ordem", cargo.getOrdem());
            queryInsert.setParameter("sigla", cargo.getSigla());
            queryInsert.executeUpdate();

            // Obter o ID do último cargo inserido
            BigInteger
                    id = (BigInteger) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
            cargo.setIdCargo(id.longValue());
        }

        List<Long> ids = cargos.stream().map(Cargo::getIdCargo).collect(Collectors.toList());

        if (ids.isEmpty()) {
            return Collections.emptyList();
        }

        List<Cargo> sortedCargos = entityManager
                .createNativeQuery("SELECT id_cargo, ativo, descricao, ordem, sigla" +
                                   " FROM cargo WHERE id_cargo IN :ids  ORDER BY id_cargo ASC", Cargo.class)
                .setParameter("ids", ids)
                .getResultList();

        return sortedCargos;
    }

    public Page <Cargo> getCargoList ( Pageable pageable ) {

        String countQuery = "SELECT count(c) FROM Cargo c";
        TypedQuery<Long> countQueryResult = entityManager.createQuery(countQuery, Long.class);
        Long countResult = countQueryResult.getSingleResult();

        String selectQuery = "SELECT c FROM Cargo c";
        TypedQuery<Cargo> selectQueryResult = entityManager.createQuery(selectQuery, Cargo.class);
        selectQueryResult.setFirstResult((int) pageable.getOffset());
        selectQueryResult.setMaxResults(pageable.getPageSize());
        List<Cargo> cargos = selectQueryResult.getResultList();

        return new PageImpl <>(cargos, pageable, countResult);
    }


    public Cargo getCargo(Integer id) throws NotFoundException {
        String sql = "SELECT id_cargo, ativo, descricao, ordem, sigla FROM cargo WHERE id_cargo = :id";
        Query query = entityManager.createNativeQuery(sql, Cargo.class);
        query.setParameter("id", id);
        List<Cargo> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            throw new NotFoundException("ID do cargo não encontrado: ", id);
        }
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

