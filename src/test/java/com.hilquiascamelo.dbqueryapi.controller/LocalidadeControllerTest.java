package com.hilquiascamelo.dbqueryapi.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hilquiascamelo.dbqueryapi.controller.CustomUtils;
import com.hilquiascamelo.dbqueryapi.controller.LocalidadeController;
import com.hilquiascamelo.dbqueryapi.dto.LocalidadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Localidade;
import com.hilquiascamelo.dbqueryapi.mapper.EntityMapper;
import com.hilquiascamelo.dbqueryapi.mapper.LocalidadeMapper;
import com.hilquiascamelo.dbqueryapi.service.LocalidadeService;
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
public class LocalidadeControllerTest {
    private static final String ENDPOINT_URL = "/api/localidade";
    @InjectMocks
    private LocalidadeController localidadeController;
    @Mock
    private LocalidadeService localidadeService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp ( ) {
        MockitoAnnotations.initMocks( this );
        mockMvc = MockMvcBuilders.standaloneSetup( localidadeController )
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build( );
    }

    @Test
    public void findAllByPage ( ) throws Exception {
        Page < LocalidadeDto > page = new PageImpl <>( Collections.singletonList( LocalidadeBuilder.getDto( ) ) );

        Mockito.when( localidadeService.findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) ) )
                .thenReturn( page );

        ResultActions
                resultActions =
                mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL )
                                .accept( MediaType.APPLICATION_JSON ) )
                        .andDo( MockMvcResultHandlers.print( ) )
                        .andExpect( MockMvcResultMatchers.status( )
                                .isOk( ) )
                        .andExpect( MockMvcResultMatchers.jsonPath( "$.data.content" , Matchers.hasSize( 1 ) ) );

        Mockito.verify( localidadeService , Mockito.times( 1 ) )
                .findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) );
        Mockito.verifyNoMoreInteractions( localidadeService );

    }

    @Test
    public void getById ( ) throws Exception {
        Mockito.when( localidadeService.findById( ArgumentMatchers.anyLong( ) ) )
                .thenReturn( LocalidadeBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL + "/1" ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) )
                .andExpect( MockMvcResultMatchers.content( )
                        .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath( "$.id" , Is.is( 1 ) ) );
        Mockito.verify( localidadeService , Mockito.times( 1 ) )
                .findById( "1" );
        Mockito.verifyNoMoreInteractions( localidadeService );
    }

    @Test
    public void save ( ) throws Exception {
        Mockito.when( localidadeService.save( ArgumentMatchers.any( LocalidadeDto.class ) ) )
                .thenReturn( LocalidadeBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.post( ENDPOINT_URL )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( LocalidadeBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isCreated( ) );
        Mockito.verify( localidadeService , Mockito.times( 1 ) )
                .save( ArgumentMatchers.any( LocalidadeDto.class ) );
        Mockito.verifyNoMoreInteractions( localidadeService );
    }

    @Test
    public void update ( ) throws Exception {
        Mockito.when( localidadeService.update( ArgumentMatchers.any( ) , ArgumentMatchers.anyLong( ) ) )
                .thenReturn( LocalidadeBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.put( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( LocalidadeBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( localidadeService , Mockito.times( 1 ) )
                .update( ArgumentMatchers.any( LocalidadeDto.class ) , ArgumentMatchers.anyLong( ) );
        Mockito.verifyNoMoreInteractions( localidadeService );
    }

    @Test
    public void delete ( ) throws Exception {
        Mockito.doNothing( )
                .when( localidadeService )
                .deleteById( ArgumentMatchers.anyLong( ) );
        mockMvc.perform( MockMvcRequestBuilders.delete( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( LocalidadeBuilder.getIds( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( localidadeService , Mockito.times( 1 ) )
                .deleteById( Mockito.anyLong( ) );
        Mockito.verifyNoMoreInteractions( localidadeService );
    }
}