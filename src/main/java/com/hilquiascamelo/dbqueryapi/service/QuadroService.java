package com.hilquiascamelo.dbqueryapi.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hilquiascamelo.dbqueryapi.dto.QuadroDto;
import com.hilquiascamelo.dbqueryapi.entity.Quadro;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.mapper.QuadroMapper;
import com.hilquiascamelo.dbqueryapi.repository.QuadroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class QuadroService {
    private final QuadroRepository repository;
    private final QuadroMapper quadroMapper;

    public QuadroService ( QuadroRepository repository ,
                           QuadroMapper quadroMapper ) {
        this.repository = repository;
        this.quadroMapper = quadroMapper;
    }

    public QuadroDto save ( QuadroDto quadroDto ) {
        Quadro entity = quadroMapper.toEntity( quadroDto );
        return quadroMapper.toDto( repository.save( entity ) );
    }

    public void deleteById ( Long id ) {
        repository.deleteById( id );
    }

    public QuadroDto findById ( Long id ) {
        return quadroMapper.toDto( repository.findById( id )
                .orElseThrow( ResourceNotFoundException::new ) );
    }

    public Page < QuadroDto > findByCondition ( QuadroDto quadroDto ,
                                                Pageable pageable ) {
        Page < Quadro > entityPage = repository.findAll( pageable );
        List < Quadro > entities = entityPage.getContent( );
        return new PageImpl <>( quadroMapper.toDto( entities ) , pageable , entityPage.getTotalElements( ) );
    }

//    public QuadroDto update ( QuadroDto quadroDto ,
//                              Long id ) {
//        QuadroDto data = findById( id );
//        Quadro entity = quadroMapper.toEntity( quadroDto );
//        BeanUtil.copyProperties( data , entity );
//        return save( quadroMapper.toDto( entity ) );
//    }
}