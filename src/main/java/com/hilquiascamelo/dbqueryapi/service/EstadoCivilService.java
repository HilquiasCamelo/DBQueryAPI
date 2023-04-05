package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.controller.LocalidadeController;
import com.hilquiascamelo.dbqueryapi.dto.EstadoCivilDto;
import com.hilquiascamelo.dbqueryapi.entity.EstadoCivil;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.mapper.EstadoCivilMapper;
import com.hilquiascamelo.dbqueryapi.repository.EstadoCivilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class EstadoCivilService {

    private static final Logger LOGGER = LoggerFactory.getLogger( LocalidadeController.class);
    private final EstadoCivilRepository repository;
    private final EstadoCivilMapper estadoCivilMapper;

    public EstadoCivilService ( EstadoCivilRepository repository ,
                                EstadoCivilMapper estadoCivilMapper ) {
        this.repository = repository;
        this.estadoCivilMapper = estadoCivilMapper;
    }

    public EstadoCivilDto save ( EstadoCivilDto estadoCivilDto ) {
        EstadoCivil entity = estadoCivilMapper.toEntity( estadoCivilDto );
        return estadoCivilMapper.toDto( repository.save( entity ) );
    }

    public void deleteById ( Long id ) {
        repository.deleteById( id );
    }

    public EstadoCivilDto findById ( Long id ) {
        return estadoCivilMapper.toDto( repository.findById( id )
                .orElseThrow( ResourceNotFoundException::new ) );
    }

    public Page < EstadoCivilDto > findByCondition ( EstadoCivilDto estadoCivilDto ,
                                                     Pageable pageable ) {
        Page < EstadoCivil > entityPage = repository.findAll( pageable );
        List < EstadoCivil > entities = entityPage.getContent( );
        return new PageImpl <>( estadoCivilMapper.toDto( entities ) , pageable , entityPage.getTotalElements( ) );
    }

//    public EstadoCivilDto update ( EstadoCivilDto estadoCivilDto ,
//                                   Long id ) {
//        EstadoCivilDto data = findById( id );
//        EstadoCivil entity = estadoCivilMapper.toEntity( estadoCivilDto );
//        BeanUtil.copyProperties( data , entity );
//        return save( estadoCivilMapper.toDto( entity ) );
//    }
}