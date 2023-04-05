package com.hilquiascamelo.dbqueryapi.mapper;

import com.hilquiascamelo.dbqueryapi.dto.QuadroDto;
import com.hilquiascamelo.dbqueryapi.entity.Quadro;
import org.mapstruct.Mapper;

@Mapper ( componentModel = "spring")
public interface QuadroMapper extends EntityMapper < QuadroDto, Quadro > {
}