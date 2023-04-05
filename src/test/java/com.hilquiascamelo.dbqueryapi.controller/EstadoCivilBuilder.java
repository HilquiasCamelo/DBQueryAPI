package com.hilquiascamelo.dbqueryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class EstadoCivilBuilder {
    public static List < String > getIds ( ) {
        return Collections.singletonList( "1" );
    }

    public static EstadoCivilDto getDto ( ) {
        EstadoCivilDto dto = new EstadoCivilDto( );
        dto.setId( "1" );
        return dto;
    }
}