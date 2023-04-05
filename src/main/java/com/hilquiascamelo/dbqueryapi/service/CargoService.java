package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoReferencedException;
import com.hilquiascamelo.dbqueryapi.exceptions.NotFoundException;
import com.hilquiascamelo.dbqueryapi.repository.CargoRepository;
import com.hilquiascamelo.dbqueryapi.service.util.JpaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CargoService {
    private static final Logger LOGGER = LoggerFactory.getLogger( CargoService.class );

    public CargoService ( CargoRepository cargoRepository ) {
        this.cargoRepository = cargoRepository;
    }

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    JpaUtils jpaUtils;

    @PersistenceContext
    private EntityManager entityManager;

    public List < Cargo > saveCargo ( List < Cargo > cargos ) {
        return cargoRepository.saveAll( cargos );
    }

    public Page < Cargo > getCargoList ( Pageable pageable ) {
        return cargoRepository.getCargoList( pageable );

    }


    public Cargo getCargo ( Integer id ) throws NotFoundException {
        if( !jpaUtils.existsById( "Cargo" , "id_cargo" , id ) ) {
            throw new NotFoundException( "ID do cargo não encontrado: " , id );
        }
        return cargoRepository.getCargo( id );
    }

    public Cargo putCargo ( Cargo cargos ,
                            Integer id ) throws NotFoundException {
        if( !jpaUtils.existsById( "Cargo" , "id_cargo" , id ) ) {
            throw new NotFoundException( "ID do cargo não encontrado: " , id );
        }

        return cargoRepository.putCargo( cargos , id );
    }

    public Integer deleteCargo ( Integer id ) throws NotFoundException {
        Objects.requireNonNull( id , "O id do cargo não pode ser nulo" );
        validarExistenciaDoCargo( id );
        validarCargoNaoReferenciado( id );
        return cargoRepository.deleteCargo( id );
    }

    private void validarExistenciaDoCargo ( Integer id ) throws NotFoundException {
        if( !jpaUtils.existsById( "Cargo" , "id_cargo" , id ) ) {
            throw new NotFoundException( "Cargo não encontrado .:" , id );
        }
    }

    private void validarCargoNaoReferenciado ( Integer id ) {
        List<String> listEntity = new ArrayList<>();
        listEntity.add( "Pessoa" );
        if( jpaUtils.isReferencedOrExistsById( listEntity , "oid_cargo_quadro_sgp" , id ) ) {
            throw new CargoReferencedException(
                    "Não é possível excluir o cargo porque ele é referenciado em outras tabelas." );
        }
    }

}
