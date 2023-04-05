/**
 * Classe responsável por fornecer serviços para a entidade Escolaridade.
 */
package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.dto.EscolaridadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.entity.Escolaridade;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.mapper.EscolaridadeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Transactional
public class EscolaridadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger( EscolaridadeRepository.class );

    private final EscolaridadeMapper escolaridadeMapper;

    public EscolaridadeRepository ( EscolaridadeMapper escolaridadeMapper ) {
        this.escolaridadeMapper = escolaridadeMapper;
    }

    /**
     * Construtor da classe EscolaridadeService
     * @param repository objeto do tipo EscolaridadeRepository
     * @param escolaridadeMapper objeto do tipo EscolaridadeMapper
     */

    private static final String
            INSERT_ESCOLARIDADE_SQL =
            "INSERT INTO escolaridade (descricao, status, nivel ) VALUES " + "(:descricao, :status, :nivel)";
    private static final String
            SELECT_ESCOLARIDADE_BY_ID_SQL =
            "SELECT id_escolaridade, descricao, status , nivel FROM escolaridade WHERE id_escolaridade = :id_escolaridade";
    private static final String
            DELETE_ESCOLARIDADE_BY_ID_SQL =
            "DELETE FROM escolaridade WHERE id_escolaridade = :id_escolaridade";
    private static final String
            UPDATE_ESCOLARIDADE_BY_ID_SQL =
            "UPDATE escolaridade SET descricao = :descricao, status = :status WHERE id_escolaridade = :id_escolaridade";
    private static final String SELECT_ESCOLARIDADE_SQL = "SELECT id_escolaridade, descricao, status FROM escolaridade";


    /**
     * Método responsável por salvar uma escolaridade no banco de dados.
     *
     * @param escolaridadeDto
     *         objeto do tipo EscolaridadeDto
     * @return escolaridadeDto objeto do tipo EscolaridadeDto
     */
    public  List<Escolaridade> saveAll(List<EscolaridadeDto> escolaridades) {
        List<Long> ids = new ArrayList <>();

        for (EscolaridadeDto escolaridade : escolaridades) {
            Query query = entityManager.createNativeQuery(INSERT_ESCOLARIDADE_SQL);
            query.setParameter("descricao", escolaridade.getDescricao());
            query.setParameter("status", escolaridade.getAtivo());
            query.setParameter("nivel", escolaridade.getNivel());
            query.executeUpdate();

            BigInteger id = (BigInteger) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
            escolaridade.setId(id.longValue());
            ids.add(escolaridade.getId());
        }

        if (ids.isEmpty()) {
            return Collections.emptyList();
        }

        List<Escolaridade> sortedEscolaridade = entityManager.createNativeQuery(
                        "SELECT id_escolaridade, descricao, status, nivel FROM Escolaridade WHERE id_escolaridade I" +
                        "N :id_escolaridades ORDER BY id_escolaridade ASC",
                        Escolaridade.class)
                .setParameter("id_escolaridades", ids)
                .getResultList();

        return sortedEscolaridade;
    }


    /**
     * Método responsável por deletar uma escolaridade do banco de dados pelo seu id.
     * @param id objeto do tipo Long
     * @return objeto do tipo Integer
     */
    public Integer deleteById ( Long id ) {
        Query query = entityManager.createNativeQuery( DELETE_ESCOLARIDADE_BY_ID_SQL );
        query.setParameter( "id_escolaridade" , id );
        return query.executeUpdate( );
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page < Escolaridade > getAll ( Pageable pageable ) {

        String countQuery = "SELECT count(c) FROM Escolaridade c";
        TypedQuery < Long > countQueryResult = entityManager.createQuery( countQuery , Long.class );
        Long countResult = countQueryResult.getSingleResult( );

        String selectQuery = "SELECT c FROM Escolaridade c";
        TypedQuery < Escolaridade > selectQueryResult = entityManager.createQuery( selectQuery , Escolaridade.class );
        selectQueryResult.setFirstResult( ( int ) pageable.getOffset( ) );
        selectQueryResult.setMaxResults( pageable.getPageSize( ) );
        List < Escolaridade > Escolaridades = selectQueryResult.getResultList( );

        return new PageImpl <>( Escolaridades , pageable , countResult );
    }

    /**

     Busca uma escolaridade pelo ID.
     @param id O ID da escolaridade a ser buscada.
     @return A escolaridade encontrada.
     @throws ResourceNotFoundException caso a escolaridade não seja encontrada.
     */

    public Escolaridade findByIdEscolaridade(Long id) {
        Query query = entityManager.createNativeQuery(SELECT_ESCOLARIDADE_BY_ID_SQL, Escolaridade.class);
        query.setParameter("id_escolaridade", id);
        List<Escolaridade> result = query.getResultList();
        if (!result.isEmpty()) {
            Escolaridade escolaridade = result.get(0);
            return escolaridade;
        } else {
            throw new ResourceNotFoundException();
        }
    }


    /**
     Atualiza a escolaridade com o id informado com os dados contidos no objeto EscolaridadeDto passado como parâmetro.

     @param escolaridadeDto o objeto contendo os dados da escolaridade a ser atualizada.

     @param id o id da escolaridade a ser atualizada.

     @return um objeto EscolaridadeDto com os dados atualizados da escolaridade.

     @throws ResourceNotFoundException caso não exista uma escolaridade com o id informado.
     */

    public EscolaridadeDto updateEscolaridade ( EscolaridadeDto escolaridadeDto ,
                                                Long id ) {
        Query query = entityManager.createNativeQuery( UPDATE_ESCOLARIDADE_BY_ID_SQL );
        query.setParameter( "descricao" , escolaridadeDto.getDescricao( ) );
        query.setParameter( "nivel" , escolaridadeDto.getNivel( ) );
        query.setParameter( "id_escolaridade" , id );
        query.executeUpdate( );

        Escolaridade
                escolaridadeAtualizada =
                ( Escolaridade ) entityManager.createNativeQuery( SELECT_ESCOLARIDADE_BY_ID_SQL , Escolaridade.class )
                        .setParameter( "id_escolaridade" , id )
                        .getSingleResult( );

        EscolaridadeDto escolaridadeDtoAtualizada = escolaridadeMapper.toDto( escolaridadeAtualizada );
        return escolaridadeDtoAtualizada;
    }

}