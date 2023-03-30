package com.hilquiascamelo.dbqueryapi.repository;


import com.hilquiascamelo.dbqueryapi.entity.Pessoa;
import com.hilquiascamelo.dbqueryapi.entity.dto.PessoaFromDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PessoaRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public List < Pessoa > findAllByOrderByNomeCompletoAsc ( ) {
        String sql = "SELECT * FROM pessoa ORDER BY nome_completo ASC";
        return entityManager.createNativeQuery ( sql , Pessoa.class )
                .getResultList ( );
    }
    public List < Pessoa > saveAllAndOrderByNomeCompletoAsc ( List < PessoaFromDTO > pessoas ) {
        String sql = new StringBuilder ( ).append ( "INSERT INTO pessoa " )
                .append ( "(cargo_id, cod_ref, cpf, data_ingresso, data_nascimento, doador_orgao, " )
                .append ( "doador_sanguineo, escolaridade_id, estado_civil_id, fator_rh, fator_sanguineo, " )
                .append ( "matricula_sgp, naturalidade_id, nome_completo, nome_foto, nome_guerra_sgp, " )
                .append ( "nome_mae, nome_pai, observacao_adicional, oid_cargo_quadro_sgp, oid_ome_sgp, " )
                .append ( "oid_pessoa_sgp, pessoa_validada, quadro_id, religiao_id, rg_militar_sgp, sexo) " )
                .append ( "VALUES " )
                .append ( "(:cargoId, :codRef, :cpf, :dataIngresso, :dataNascimento, :doadorOrgao, " )
                .append ( ":doadorSanguineo, :escolaridadeId, :estadoCivilId, :fatorRH, :fatorSanguineo, " )
                .append ( ":matriculaSgp, :naturalidadeId, :nomeCompleto, :nomeFoto, :nomeGuerraSgp, " )
                .append ( ":nomeMae, :nomePai, :observacaoAdicional, :oidCargoQuadroSgp, :oidOmeSgp, " )
                .append ( ":oidPessoaSgp, :pessoaValidada, :quadroId, :religiaoId, :rgMilitarSgp, :sexo)" )
                .toString ( );

        Query query = entityManager.createNativeQuery ( sql , Pessoa.class );

        for (PessoaFromDTO pessoa : pessoas) {
            query.setParameter ( "cargoId" , pessoa.getCargo ( )
                    .getIdCargo ( ) );
            query.setParameter ( "codRef" , pessoa.getCodigoReferencia ( ) );
            query.setParameter ( "cpf" , pessoa.getCpf ( ) );
            query.setParameter ( "dataIngresso" , pessoa.getDataDeIngresso ( ) );
            query.setParameter ( "dataNascimento" , pessoa.getDataDeNascimento ( ) );
            query.setParameter ( "doadorOrgao" , pessoa.isDoadorDeOrgaos ( ) );
            query.setParameter ( "doadorSanguineo" , pessoa.isDoadorDeSangue ( ) );
            query.setParameter ( "escolaridadeId" , pessoa.getEscolaridade ( ) );
            query.setParameter ( "estadoCivilId" , pessoa.getEstadoCivil ( ) );
            query.setParameter ( "fatorRH" , pessoa.getFatorRH ( ) );
            query.setParameter ( "fatorSanguineo" , pessoa.getFatorSanguineo ( ) );
            query.setParameter ( "matriculaSgp" , pessoa.getMatricula ( ) );
            query.setParameter ( "naturalidadeId" , pessoa.getNaturalidade ( )
                    .getIdLocalidade ( ) );
            query.setParameter ( "nomeCompleto" , pessoa.getNomeCompleto ( ) );
            query.setParameter ( "nomeFoto" , pessoa.getFoto ( ) );
            query.setParameter ( "nomeGuerraSgp" , pessoa.getApelido ( ) );
            query.setParameter ( "nomeMae" , pessoa.getNomeDaMae ( ) );
            query.setParameter ( "nomePai" , pessoa.getNomeDoPai ( ) );
            query.setParameter ( "observacaoAdicional" , pessoa.getObservacoesAdicionais ( ) );
            query.setParameter ( "oidCargoQuadroSgp" , pessoa.getOidCargoQuadroSgp ( ) );
            query.setParameter ( "oidOmeSgp" , pessoa.getOidOmeSgp ( ) );
            query.setParameter ( "oidPessoaSgp" , pessoa.getOidPessoaSgp ( ) );
            query.setParameter ( "pessoaValidada" , pessoa.isPessoaValidada ( ) );
            query.setParameter ( "quadro.id" , pessoa.getQuadro ( )
                    .getIdQuadro ( ) );
            query.setParameter ( "religiao.id" , pessoa.getReligiao ( )
                    .getId_religiao ( ) );
            query.setParameter ( "rgMilitarSgp" , pessoa.getRgCivil ( ) );
            query.setParameter ( "sexo" , pessoa.getSexo ( ) );
            query.executeUpdate ( );
        }

        List < Pessoa > sortedPessoas = entityManager.createQuery ( "SELECT p FROM Pessoa p ORDER BY p.nomeCompleto ASC" , Pessoa.class )
                .getResultList ( );
        return sortedPessoas;

    }
}