package com.hilquiascamelo.dbqueryapi.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hilquiascamelo.dbqueryapi.controller.CustomUtils;
import com.hilquiascamelo.dbqueryapi.controller.QuadroController;
import com.hilquiascamelo.dbqueryapi.dto.QuadroDto;
import com.hilquiascamelo.dbqueryapi.entity.Quadro;
import com.hilquiascamelo.dbqueryapi.mapper.EntityMapper;
import com.hilquiascamelo.dbqueryapi.mapper.QuadroMapper;
import com.hilquiascamelo.dbqueryapi.service.QuadroService;
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
public class QuadroControllerTest {
    private static final String ENDPOINT_URL = "/api/quadro";
    @InjectMocks
    private QuadroController quadroController;
    @Mock
    private QuadroService quadroService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp ( ) {
        MockitoAnnotations.initMocks( this );
        mockMvc = MockMvcBuilders.standaloneSetup( quadroController )
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build( );
    }

    @Test
    public void findAllByPage ( ) throws Exception {
        Page < QuadroDto > page = new PageImpl <>( Collections.singletonList( QuadroBuilder.getDto( ) ) );

        Mockito.when( quadroService.findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) ) )
                .thenReturn( page );

        ResultActions
                resultActions =
                mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL )
                                .accept( MediaType.APPLICATION_JSON ) )
                        .andDo( MockMvcResultHandlers.print( ) )
                        .andExpect( MockMvcResultMatchers.status( )
                                .isOk( ) )
                        .andExpect( MockMvcResultMatchers.jsonPath( "$.data.content" , Matchers.hasSize( 1 ) ) );

        Mockito.verify( quadroService , Mockito.times( 1 ) )
                .findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) );
        Mockito.verifyNoMoreInteractions( quadroService );

    }

    @Test
    public void getById ( ) throws Exception {
        Mockito.when( quadroService.findById( ArgumentMatchers.anyLong( ) ) )
                .thenReturn( QuadroBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL + "/1" ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) )
                .andExpect( MockMvcResultMatchers.content( )
                        .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath( "$.id" , Is.is( 1 ) ) );
        Mockito.verify( quadroService , Mockito.times( 1 ) )
                .findById( "1" );
        Mockito.verifyNoMoreInteractions( quadroService );
    }

    @Test
    public void save ( ) throws Exception {
        Mockito.when( quadroService.save( ArgumentMatchers.any( QuadroDto.class ) ) )
                .thenReturn( QuadroBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.post( ENDPOINT_URL )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( QuadroBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isCreated( ) );
        Mockito.verify( quadroService , Mockito.times( 1 ) )
                .save( ArgumentMatchers.any( QuadroDto.class ) );
        Mockito.verifyNoMoreInteractions( quadroService );
    }

    @Test
    public void update ( ) throws Exception {
        Mockito.when( quadroService.update( ArgumentMatchers.any( ) , ArgumentMatchers.anyLong( ) ) )
                .thenReturn( QuadroBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.put( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( QuadroBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( quadroService , Mockito.times( 1 ) )
                .update( ArgumentMatchers.any( QuadroDto.class ) , ArgumentMatchers.anyLong( ) );
        Mockito.verifyNoMoreInteractions( quadroService );
    }

    @Test
    public void delete ( ) throws Exception {
        Mockito.doNothing( )
                .when( quadroService )
                .deleteById( ArgumentMatchers.anyLong( ) );
        mockMvc.perform( MockMvcRequestBuilders.delete( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( QuadroBuilder.getIds( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( quadroService , Mockito.times( 1 ) )
                .deleteById( Mockito.anyLong( ) );
        Mockito.verifyNoMoreInteractions( quadroService );
    }
}