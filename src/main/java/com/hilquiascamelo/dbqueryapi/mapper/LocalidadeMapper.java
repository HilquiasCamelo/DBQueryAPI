package com.hilquiascamelo.dbqueryapi.mapper;

import com.hilquiascamelo.dbqueryapi.dto.LocalidadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Localidade;
import org.mapstruct.Mapper;

@Mapper ( componentModel = "spring")
public interface LocalidadeMapper extends EntityMapper < LocalidadeDto, Localidade > {
}