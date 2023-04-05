package com.hilquiascamelo.dbqueryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LocalidadeBuilder {
    public static List < String > getIds ( ) {
        return Collections.singletonList( "1" );
    }

    public static LocalidadeDto getDto ( ) {
        LocalidadeDto dto = new LocalidadeDto( );
        dto.setId( "1" );
        return dto;
    }
}