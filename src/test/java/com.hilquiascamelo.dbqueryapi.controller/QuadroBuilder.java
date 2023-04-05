package com.hilquiascamelo.dbqueryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilquiascamelo.dbqueryapi.dto.QuadroDto;

import java.util.Collections;
import java.util.List;

public class QuadroBuilder {
    public static List < String > getIds ( ) {
        return Collections.singletonList( "1" );
    }

    public static QuadroDto getDto ( ) {
        QuadroDto dto = new QuadroDto( );
        dto.setId( 1l );
        return dto;
    }
}