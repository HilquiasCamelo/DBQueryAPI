package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoNotFoundException;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoReferencedException;
import com.hilquiascamelo.dbqueryapi.repository.CargoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.List;

@Service
public class CargoService {
    public CargoService ( CargoRepository cargoRepository ) {
        this.cargoRepository = cargoRepository;
    }

    @Autowired CargoRepository cargoRepository;

    public List < Cargo > saveCargo ( List < Cargo > cargos ) {
        return cargoRepository.saveAll ( cargos );
    }

    public List < Cargo > getCargoList ( ) {
        return cargoRepository.getCargoList ( );

    }

    public Cargo  putCargo ( Cargo cargos ,  Integer id  ) {
        return cargoRepository.putCargo(cargos, id);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CargoService.class );



    @PersistenceContext
    private EntityManager entityManager;

    public Integer deleteCargo(Integer id) throws IllegalArgumentException {
        try {
            if (existsById(id)) {
                if (isCargoReferenced(id)) {
                    throw new CargoReferencedException("Não é possível excluir o cargo porque ele é referenciado em outras tabelas.");
                }
                return cargoRepository.deleteCargo(id);
            } else {
                throw new CargoNotFoundException("ID do cargo não encontrado: ", id);
            }
        } catch ( CargoNotFoundException e) {
            LOGGER.error("Erro ao excluir cargo: " + e.getMessage());
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage( ));
        } catch ( CargoReferencedException e) {
            LOGGER.error("Erro ao excluir cargo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Erro ao excluir cargo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro ao excluir o cargo");
        }
    }

    private boolean isCargoReferenced(Integer id) {
        TypedQuery <Long>
                query = entityManager.createQuery("SELECT COUNT(*) FROM Pessoa WHERE cargo.id_cargo = :id", Long.class );
        query.setParameter("id", id.longValue());

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