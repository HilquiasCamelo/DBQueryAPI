package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.controller.ReligiaoController;
import com.hilquiascamelo.dbqueryapi.dto.ReligiaoDto;
import com.hilquiascamelo.dbqueryapi.entity.Religiao;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.mapper.ReligiaoMapper;
import com.hilquiascamelo.dbqueryapi.repository.ReligiaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
    public class ReligiaoService {

        private static final Logger LOGGER = LoggerFactory.getLogger( ReligiaoService.class );
        private final ReligiaoRepository repository;
        private final ReligiaoMapper religiaoMapper;

        public ReligiaoService ( ReligiaoRepository repository ,
                                 ReligiaoMapper religiaoMapper ) {
            this.repository = repository;
            this.religiaoMapper = religiaoMapper;
        }

        public ReligiaoDto save ( ReligiaoDto religiaoDto ) {
            Religiao entity = religiaoMapper.toEntity( religiaoDto );
            return religiaoMapper.toDto( repository.save( entity ) );
        }

        public void deleteById ( Long id ) {
            repository.deleteById( id );
        }

        public ReligiaoDto findById ( Long id ) {
            return religiaoMapper.toDto( repository.findById( id )
                    .orElseThrow( ResourceNotFoundException::new ) );
        }

        public Page < ReligiaoDto > findByCondition ( ReligiaoDto religiaoDto ,
                                                      Pageable pageable ) {
            Page < Religiao > entityPage = repository.findAll( pageable );
            List < Religiao > entities = entityPage.getContent( );
            return new PageImpl <>( religiaoMapper.toDto( entities ) , pageable , entityPage.getTotalElements( ) );
        }

//        public ReligiaoDto update ( ReligiaoDto religiaoDto ,
//                                    Long id ) {
//            ReligiaoDto data = findById( id );
//            Religiao entity = religiaoMapper.toEntity( religiaoDto );
//            BeanUtil.copyProperties( data , entity );
//            return save( religiaoMapper.toDto( entity ) );
//        }
    }