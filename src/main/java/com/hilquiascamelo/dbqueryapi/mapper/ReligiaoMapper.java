package com.hilquiascamelo.dbqueryapi.mapper;

import com.hilquiascamelo.dbqueryapi.dto.ReligiaoDto;
import com.hilquiascamelo.dbqueryapi.entity.Religiao;
import org.mapstruct.Mapper;

@Mapper ( componentModel = "spring")
public interface ReligiaoMapper extends EntityMapper < ReligiaoDto, Religiao > {
}