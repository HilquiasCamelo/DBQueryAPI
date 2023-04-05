package com.hilquiascamelo.dbqueryapi.dto;

import io.swagger.annotations.ApiModel;

@ApiModel ()
public class QuadroDto extends AbstractDto < Long > {
    private Long idQuadro;
    private boolean ativo;
    private String descricao;
    private String sigla;
    private String tipo;

    public QuadroDto ( ) {
    }

    public void setIdQuadro ( Long idQuadro ) {
        this.idQuadro = idQuadro;
    }

    public Long getIdQuadro ( ) {
        return this.idQuadro;
    }

    public void setAtivo ( boolean ativo ) {
        this.ativo = ativo;
    }

    public boolean getAtivo ( ) {
        return this.ativo;
    }

    public void setDescricao ( String descricao ) {
        this.descricao = descricao;
    }

    public String getDescricao ( ) {
        return this.descricao;
    }

    public void setSigla ( String sigla ) {
        this.sigla = sigla;
    }

    public String getSigla ( ) {
        return this.sigla;
    }

    public void setTipo ( String tipo ) {
        this.tipo = tipo;
    }

    public String getTipo ( ) {
        return this.tipo;
    }
}