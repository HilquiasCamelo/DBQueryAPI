package com.hilquiascamelo.dbqueryapi.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hilquiascamelo.dbqueryapi.controller.CustomUtils;
import com.hilquiascamelo.dbqueryapi.controller.EstadoCivilController;
import com.hilquiascamelo.dbqueryapi.dto.EstadoCivilDto;
import com.hilquiascamelo.dbqueryapi.entity.EstadoCivil;
import com.hilquiascamelo.dbqueryapi.mapper.EntityMapper;
import com.hilquiascamelo.dbqueryapi.mapper.EstadoCivilMapper;
import com.hilquiascamelo.dbqueryapi.service.EstadoCivilService;
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
public class EstadoCivilControllerTest {
    private static final String ENDPOINT_URL = "/api/estado-civil";
    @InjectMocks
    private EstadoCivilController estadocivilController;
    @Mock
    private EstadoCivilService estadocivilService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp ( ) {
        MockitoAnnotations.initMocks( this );
        mockMvc = MockMvcBuilders.standaloneSetup( estadocivilController )
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build( );
    }

    @Test
    public void findAllByPage ( ) throws Exception {
        Page < EstadoCivilDto > page = new PageImpl <>( Collections.singletonList( EstadoCivilBuilder.getDto( ) ) );

        Mockito.when( estadocivilService.findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) ) )
                .thenReturn( page );

        ResultActions
                resultActions =
                mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL )
                                .accept( MediaType.APPLICATION_JSON ) )
                        .andDo( MockMvcResultHandlers.print( ) )
                        .andExpect( MockMvcResultMatchers.status( )
                                .isOk( ) )
                        .andExpect( MockMvcResultMatchers.jsonPath( "$.data.content" , Matchers.hasSize( 1 ) ) );

        Mockito.verify( estadocivilService , Mockito.times( 1 ) )
                .findByCondition( ArgumentMatchers.any( ) , ArgumentMatchers.any( ) );
        Mockito.verifyNoMoreInteractions( estadocivilService );

    }

    @Test
    public void getById ( ) throws Exception {
        Mockito.when( estadocivilService.findById( ArgumentMatchers.anyLong( ) ) )
                .thenReturn( EstadoCivilBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.get( ENDPOINT_URL + "/1" ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) )
                .andExpect( MockMvcResultMatchers.content( )
                        .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath( "$.id" , Is.is( 1 ) ) );
        Mockito.verify( estadocivilService , Mockito.times( 1 ) )
                .findById( "1" );
        Mockito.verifyNoMoreInteractions( estadocivilService );
    }

    @Test
    public void save ( ) throws Exception {
        Mockito.when( estadocivilService.save( ArgumentMatchers.any( EstadoCivilDto.class ) ) )
                .thenReturn( EstadoCivilBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.post( ENDPOINT_URL )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( EstadoCivilBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isCreated( ) );
        Mockito.verify( estadocivilService , Mockito.times( 1 ) )
                .save( ArgumentMatchers.any( EstadoCivilDto.class ) );
        Mockito.verifyNoMoreInteractions( estadocivilService );
    }

    @Test
    public void update ( ) throws Exception {
        Mockito.when( estadocivilService.update( ArgumentMatchers.any( ) , ArgumentMatchers.anyLong( ) ) )
                .thenReturn( EstadoCivilBuilder.getDto( ) );

        mockMvc.perform( MockMvcRequestBuilders.put( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( EstadoCivilBuilder.getDto( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( estadocivilService , Mockito.times( 1 ) )
                .update( ArgumentMatchers.any( EstadoCivilDto.class ) , ArgumentMatchers.anyLong( ) );
        Mockito.verifyNoMoreInteractions( estadocivilService );
    }

    @Test
    public void delete ( ) throws Exception {
        Mockito.doNothing( )
                .when( estadocivilService )
                .deleteById( ArgumentMatchers.anyLong( ) );
        mockMvc.perform( MockMvcRequestBuilders.delete( ENDPOINT_URL + "/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( CustomUtils.asJsonString( EstadoCivilBuilder.getIds( ) ) ) )
                .andExpect( MockMvcResultMatchers.status( )
                        .isOk( ) );
        Mockito.verify( estadocivilService , Mockito.times( 1 ) )
                .deleteById( Mockito.anyLong( ) );
        Mockito.verifyNoMoreInteractions( estadocivilService );
    }
}