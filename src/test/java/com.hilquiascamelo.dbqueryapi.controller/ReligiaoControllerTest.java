package com.hilquiascamelo.dbqueryapi.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hilquiascamelo.dbqueryapi.controller.CustomUtils;
import com.hilquiascamelo.dbqueryapi.controller.ReligiaoController;
import com.hilquiascamelo.dbqueryapi.dto.ReligiaoDto;
import com.hilquiascamelo.dbqueryapi.entity.Religiao;
import com.hilquiascamelo.dbqueryapi.mapper.EntityMapper;
import com.hilquiascamelo.dbqueryapi.mapper.ReligiaoMapper;
import com.hilquiascamelo.dbqueryapi.service.ReligiaoService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class ReligiaoControllerTest {
    private static final String ENDPOINT_URL = "/api/religiao";
    @InjectMocks
    private ReligiaoController religiaoController;
    @Mock
    private ReligiaoService religiaoService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp ( ) {
        MockitoAnnotations.initMocks( this );
        mockMvc = MockMvcBuilders.standaloneSetup( religiaoController )
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build( );
    }

    @Test
    public void findAllByPage ( ) throws Exception {
        Page < ReligiaoDto > page = new PageImpl <>( Collections.singletonList( ReligiaoBuilder.getDto( ) ) );

        Mockito.when( religiaoService.findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) ) )
                .thenReturn( page );

        ResultActions
                resultActions =
                mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL )
                                .accept( MediaType.APPLICATION_JSON ) )
                        .andDo( MockMvcResultHandlers.print( ) )
                        .andExpect( MockMvcResultMatchers.status( )
                                .isOk( ) )
                        .andExpect( MockMvcResultMatchers.jsonPath( "$.data.content" , Matchers.hasSize( 1 ) ) );

        Mockito.verify( religiaoService , Mockito.times( 1 ) )
                .findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) );
        Mockito.verifyNoMoreInteractions( religiaoService );

    }

    @Test
    public void getById ( ) throws Exception {
        Mockito.when( religiaoService.findById( ArgumentMatchers.anyLong( ) ) )
                .thenReturn( ReligiaoBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL + "/1" ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) )
                .andExpect( MockMvcResultMatchers.content( )
                        .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath( "$.id" , Is.is( 1 ) ) );
        Mockito.verify( religiaoService , Mockito.times( 1 ) )
                .findById( "1" );
        Mockito.verifyNoMoreInteractions( religiaoService );
    }

    @Test
    public void save ( ) throws Exception {
        Mockito.when( religiaoService.save( ArgumentMatchers.any( ReligiaoDto.class ) ) )
                .thenReturn( ReligiaoBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.post( ENDPOINT_URL )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( ReligiaoBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isCreated( ) );
        Mockito.verify( religiaoService , Mockito.times( 1 ) )
                .save( ArgumentMatchers.any( ReligiaoDto.class ) );
        Mockito.verifyNoMoreInteractions( religiaoService );
    }

    @Test
    public void update ( ) throws Exception {
        Mockito.when( religiaoService.update( ArgumentMatchers.any( ) , ArgumentMatchers.anyLong( ) ) )
                .thenReturn( ReligiaoBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.put( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( ReligiaoBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( religiaoService , Mockito.times( 1 ) )
                .update( ArgumentMatchers.any( ReligiaoDto.class ) , ArgumentMatchers.anyLong( ) );
        Mockito.verifyNoMoreInteractions( religiaoService );
    }

    @Test
    public void delete ( ) throws Exception {
        Mockito.doNothing( )
                .when( religiaoService )
                .deleteById( ArgumentMatchers.anyLong( ) );
        mockMvc.perform( MockMvcRequestBuilders.delete( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( ReligiaoBuilder.getIds( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( religiaoService , Mockito.times( 1 ) )
                .deleteById( Mockito.anyLong( ) );
        Mockito.verifyNoMoreInteractions( religiaoService );
    }
}