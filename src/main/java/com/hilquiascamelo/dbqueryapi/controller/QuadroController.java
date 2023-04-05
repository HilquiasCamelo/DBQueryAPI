package com.hilquiascamelo.dbqueryapi.controller;

import com.hilquiascamelo.dbqueryapi.dto.QuadroDto;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.service.QuadroService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping ( "/api/quadro")
@RestController

public class QuadroController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalidadeController.class);
    private final QuadroService quadroService;

    public QuadroController ( QuadroService quadroService ) {
        this.quadroService = quadroService;
    }

    @PostMapping
    public ResponseEntity < Void > save ( @RequestBody @Validated QuadroDto quadroDto ) {
        quadroService.save( quadroDto );
        return ResponseEntity.ok( )
                .build( );
    }

    @GetMapping ( "/{id}")
    public ResponseEntity < QuadroDto > findById ( @PathVariable ( "id") Long id ) {
        QuadroDto quadro = quadroService.findById( id );
        return ResponseEntity.ok( quadro );
    }

    @DeleteMapping ( "/{id}")
    public ResponseEntity < Void > delete ( @PathVariable ( "id") Long id ) {
        Optional.ofNullable( quadroService.findById( id ) )
                .orElseThrow( ( ) -> {
                    LOGGER.error( "Unable to delete non-existent data！" );
                    return new ResourceNotFoundException( );
                } );
        quadroService.deleteById( id );
        return ResponseEntity.ok( )
                .build( );
    }

    @GetMapping ( "/page-query")
    public ResponseEntity < Page < QuadroDto > > pageQuery ( QuadroDto quadroDto ,
                                                             @PageableDefault ( sort = "createAt", direction = Sort.Direction.DESC)
                                                             Pageable pageable ) {
        Page < QuadroDto > quadroPage = quadroService.findByCondition( quadroDto , pageable );
        return ResponseEntity.ok( quadroPage );
    }

//    @PutMapping ( "/{id}")
//    public ResponseEntity < Void > update ( @RequestBody @Validated QuadroDto quadroDto ,
//                                            @PathVariable ( "id") Long id ) {
//        quadroService.update( quadroDto , id );
//        return ResponseEntity.ok( )
//                .build( );
//    }
}