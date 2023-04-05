package com.hilquiascamelo.dbqueryapi.mapper;

import com.hilquiascamelo.dbqueryapi.dto.EstadoCivilDto;
import com.hilquiascamelo.dbqueryapi.entity.EstadoCivil;
import org.mapstruct.Mapper;

@Mapper ( componentModel = "spring")
public interface EstadoCivilMapper extends EntityMapper < EstadoCivilDto, EstadoCivil > {
}