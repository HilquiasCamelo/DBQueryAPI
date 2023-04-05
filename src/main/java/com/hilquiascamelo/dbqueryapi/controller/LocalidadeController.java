package com.hilquiascamelo.dbqueryapi.controller;

import com.hilquiascamelo.dbqueryapi.dto.LocalidadeDto;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.service.LocalidadeService;
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

@RequestMapping ( "/localidade")
@RestController

public class LocalidadeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalidadeController.class);
    private final LocalidadeService localidadeService;

    public LocalidadeController ( LocalidadeService localidadeService ) {
        this.localidadeService = localidadeService;
    }

    @PostMapping
    public ResponseEntity < Void > save ( @RequestBody @Validated LocalidadeDto localidadeDto ) {
        localidadeService.save( localidadeDto );
        return ResponseEntity.ok( )
                .build( );
    }

    @GetMapping ( "/{id}")
    public ResponseEntity < LocalidadeDto > findById ( @PathVariable ( "id") Long id ) {
        LocalidadeDto localidade = localidadeService.findById( id );
        return ResponseEntity.ok( localidade );
    }

    @DeleteMapping ( "/{id}")
    public ResponseEntity < Void > delete ( @PathVariable ( "id") Long id ) {
        Optional.ofNullable( localidadeService.findById( id ) )
                .orElseThrow( ( ) -> {
                    LOGGER.error( "Unable to delete non-existent data！" );
                    return new ResourceNotFoundException( );
                } );
        localidadeService.deleteById( id );
        return ResponseEntity.ok( )
                .build( );
    }

    @GetMapping ( "/page-query")
    public ResponseEntity < Page < LocalidadeDto > > pageQuery ( LocalidadeDto localidadeDto ,
                                                                 @PageableDefault ( sort = "createAt", direction = Sort.Direction.DESC)
                                                                 Pageable pageable ) {
        Page < LocalidadeDto > localidadePage = localidadeService.findByCondition( localidadeDto , pageable );
        return ResponseEntity.ok( localidadePage );
    }

//    @PutMapping ( "/{id}")
//    public ResponseEntity < Void > update ( @RequestBody @Validated LocalidadeDto localidadeDto ,
//                                            @PathVariable ( "id") Long id ) {
//        localidadeService.update( localidadeDto , id );
//        return ResponseEntity.ok( )
//                .build( );
//    }
}