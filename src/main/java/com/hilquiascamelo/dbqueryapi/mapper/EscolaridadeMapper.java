package com.hilquiascamelo.dbqueryapi.mapper;

import com.hilquiascamelo.dbqueryapi.dto.EscolaridadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Escolaridade;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper ( componentModel = "spring")
public interface EscolaridadeMapper extends EntityMapper < EscolaridadeDto, Escolaridade > {
}