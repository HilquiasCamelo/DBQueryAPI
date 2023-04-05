package com.hilquiascamelo.dbqueryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilquiascamelo.dbqueryapi.dto.EscolaridadeDto;

import java.util.Collections;
import java.util.List;

public class EscolaridadeBuilder {
    public static List < String > getIds ( ) {
        return Collections.singletonList( "1" );
    }

    public static EscolaridadeDto getDto ( ) {
        EscolaridadeDto dto = new EscolaridadeDto( );
        dto.setId(1L);
        return dto;
    }
}