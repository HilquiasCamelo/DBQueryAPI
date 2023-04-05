package com.hilquiascamelo.dbqueryapi.controller;

import com.hilquiascamelo.dbqueryapi.dto.EstadoCivilDto;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.service.EstadoCivilService;

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

@RequestMapping ( "/estado-civil")
@RestController

public class EstadoCivilController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalidadeController.class);
    private final EstadoCivilService estadoCivilService;

    public EstadoCivilController ( EstadoCivilService estadoCivilService ) {
        this.estadoCivilService = estadoCivilService;
    }

    @PostMapping
    public ResponseEntity < Void > save ( @RequestBody @Validated EstadoCivilDto estadoCivilDto ) {
        estadoCivilService.save( estadoCivilDto );
        return ResponseEntity.ok( )
                .build( );
    }

    @GetMapping ( "/{id}")
    public ResponseEntity < EstadoCivilDto > findById ( @PathVariable ( "id") Long id ) {
        EstadoCivilDto estadoCivil = estadoCivilService.findById( id );
        return ResponseEntity.ok( estadoCivil );
    }

    @DeleteMapping ( "/{id}")
    public ResponseEntity < Void > delete ( @PathVariable ( "id") Long id ) {
        Optional.ofNullable( estadoCivilService.findById( id ) )
                .orElseThrow( ( ) -> {
                    LOGGER.error( "Unable to delete non-existent data！" );
                    return new ResourceNotFoundException( );
                } );
        estadoCivilService.deleteById( id );
        return ResponseEntity.ok( )
                .build( );
    }

    @GetMapping ( "/page-query")
    public ResponseEntity < Page < EstadoCivilDto > > pageQuery ( EstadoCivilDto estadoCivilDto ,
                                                                  @PageableDefault ( sort = "createAt", direction = Sort.Direction.DESC)
                                                                  Pageable pageable ) {
        Page < EstadoCivilDto > estadoCivilPage = estadoCivilService.findByCondition( estadoCivilDto , pageable );
        return ResponseEntity.ok( estadoCivilPage );
    }

//    @PutMapping ( "/{id}")
//    public ResponseEntity < Void > update ( @RequestBody @Validated EstadoCivilDto estadoCivilDto ,
//                                            @PathVariable ( "id") Long id ) {
//        estadoCivilService.update( estadoCivilDto , id );
//        return ResponseEntity.ok( )
//                .build( );
//    }
}