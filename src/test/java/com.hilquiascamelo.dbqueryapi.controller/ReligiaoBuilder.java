package com.hilquiascamelo.dbqueryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ReligiaoBuilder {
    public static List < String > getIds ( ) {
        return Collections.singletonList( "1" );
    }

    public static ReligiaoDto getDto ( ) {
        ReligiaoDto dto = new ReligiaoDto( );
        dto.setId( "1" );
        return dto;
    }
}