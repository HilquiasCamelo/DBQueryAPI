package com.hilquiascamelo.dbqueryapi.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hilquiascamelo.dbqueryapi.dto.LocalidadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Localidade;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.mapper.LocalidadeMapper;
import com.hilquiascamelo.dbqueryapi.repository.LocalidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class LocalidadeService {
    private final LocalidadeRepository repository;
    private final LocalidadeMapper localidadeMapper;

    public LocalidadeService ( LocalidadeRepository repository ,
                               LocalidadeMapper localidadeMapper ) {
        this.repository = repository;
        this.localidadeMapper = localidadeMapper;
    }

    public LocalidadeDto save ( LocalidadeDto localidadeDto ) {
        Localidade entity = localidadeMapper.toEntity( localidadeDto );
        return localidadeMapper.toDto( repository.save( entity ) );
    }

    public void deleteById ( Long id ) {
        repository.deleteById( id );
    }

    public LocalidadeDto findById ( Long id ) {
        return localidadeMapper.toDto( repository.findById( id )
                .orElseThrow( ResourceNotFoundException::new ) );
    }

    public Page < LocalidadeDto > findByCondition ( LocalidadeDto localidadeDto ,
                                                    Pageable pageable ) {
        Page < Localidade > entityPage = repository.findAll( pageable );
        List < Localidade > entities = entityPage.getContent( );
        return new PageImpl <>( localidadeMapper.toDto( entities ) , pageable , entityPage.getTotalElements( ) );
    }

//   @PutMapping ( "/{id}")
//    public ResponseEntity < Void > update ( @RequestBody @Validated LocalidadeDto quadroDto ,
//                                            @PathVariable ( "id") Long id ) {
//        localidadeMapper.update( quadroDto , id );
//        return ResponseEntity.ok( )
//                .build( );
//    }
}